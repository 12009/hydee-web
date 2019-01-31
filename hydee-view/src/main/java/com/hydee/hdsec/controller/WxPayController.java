package com.hydee.hdsec.controller;

import com.hydee.hdsec.entity.CompanyAccountRecharge;
import com.hydee.hdsec.entity.ErrorLog;
import com.hydee.hydee.service.CompanyAccountRechargeService;
import com.hydee.hydee.service.ErrorLogService;
import com.hydee.hdsec.util.*;
import com.hydee.hdsec.vo.JsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by King.Liu
 * 2016/8/24.
 */
@Controller
@RequestMapping(value = "/wxpay")
public class WxPayController extends BaseController{
    @Autowired
    CompanyAccountRechargeService companyAccountRechargeService;
    @Autowired
    ErrorLogService errorLogService;

    @RequestMapping(value = "/orderquery")
    @ResponseBody
    public Object orderquery(String outTradeNo,HttpServletRequest request,HttpServletResponse response) throws Exception{
        JsonVo<String> jsonObj = new JsonVo<String>();
        // 构造参数调用查询订单接口
        String result = HttpClientWX.orderQuery(outTradeNo);// 调用微信支付查询订单接口
        Map<String, String> map = HttpClientInvokeWX.resolveXmlString(result);
        if("SUCCESS".equals(map.get("trade_state"))){
            jsonObj.setResult(true);
        }else{
            jsonObj.setResult(false);
            jsonObj.setData(map.get("trade_state"));
        }
        return jsonObj;
    }

    /**
     * 生成二维码
     * @param result
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/wxQrCode")
    public void unifiedOrder(String result,HttpServletRequest request,HttpServletResponse response) throws Exception{
        try {
            Map<String, String> map = HttpClientInvokeWX.resolveXmlString(result);
            HttpClientWX.createdImage(map.get("code_url"),response);
        } catch (Exception e) {
            Logger.error("微信回调接收参数失败", e);
            e.printStackTrace();
        }
    }

    /**
     * 微信回调地址
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/wxReturnPay")
    public void wxReturnPay(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String resultXML = "";
        try {
            String result = HttpClientUtils.getReturnStream(request);// 获取微信调用我们notify_url的返回信息
            Logger.info(result+"notify_url result");
            Map<String, String> map = HttpClientInvokeWX.resolveXmlString(result);
            if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")
                    && map.get("return_code").toString().equalsIgnoreCase("SUCCESS")) {
                Logger.info("return success WxPay");
                String _outTradeNo = map.get("out_trade_no").toString();
                String _totalFee = map.get("total_fee").toString();
                resultXML = HttpClientWX.setXML("SUCCESS", "OK");
                CompanyAccountRecharge accountRecharge = new CompanyAccountRecharge();
                try {
                    accountRecharge = companyAccountRechargeService.selectBySerialNumber(_outTradeNo);
                    companyAccountRechargeService.updateRechargeStruts(accountRecharge.getId(),1);
                } catch (Exception e) {//入库失败后回退订单
                    String remark = "outTradeNo:"+_outTradeNo+",totalFee:"+_totalFee;
                    ErrorLog errorLog = new ErrorLog();
                    errorLog.setErrorMsg("入库失败后回退订单，退款");
                    errorLog.setErrorSource("wxReturnPay接口");
                    errorLog.setErrorSourceId(_outTradeNo);
                    errorLog.setErrorType("微信错误");
                    errorLog.setRemark(remark);
                    errorLogService.insertErrorLog(errorLog);
                    String refundResult = HttpClientWX.reFund(_outTradeNo,_totalFee);
                    Map<String, String> refundMap = HttpClientInvokeWX.resolveXmlString(refundResult);
                    if (refundMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")
                            && refundMap.get("return_code").toString().equalsIgnoreCase("SUCCESS")) {
                        companyAccountRechargeService.updateRechargeStruts(accountRecharge.getId(),3);
                    }else{
                        errorLog.setErrorMsg("退款失败");
                        errorLog.setErrorSource("微信refund接口"+refundMap.get("err_code_des"));
                        errorLog.setErrorSourceId(_outTradeNo);
                        errorLog.setErrorType("微信错误");
                        errorLog.setRemark(remark);
                        errorLogService.insertErrorLog(errorLog);
                    }
                }
            } else {
                resultXML = "服务器支付请求失败";
            }
        } catch (Exception e) {
            resultXML = "告知服务器支付成功请求失败";// TODO: handle exception
            Logger.error(resultXML,e);
        }
        response.setHeader("Content-Type", "text/xml; charset=UTF-8");
        PrintWriter wr = response.getWriter();
        wr.write(resultXML);
        wr.flush();
        wr.close();
    }

    public static void main(String[] args) throws Exception {
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", HttpClientWX.WXAPPID);// 公众账号ID
        parameters.put("mch_id", HttpClientWX.MCHID);// 商户号
        parameters.put("nonce_str", HttpClientWX.getNonceStr());// 随机字符串
        parameters.put("body", "厂家培训-充值");
        parameters.put("out_trade_no", HttpClientWX.getNonceStr());// 商户订单号
        parameters.put("total_fee", "1");// 商品金额,以分为单位
        parameters.put("spbill_create_ip", "127.0.0.1");// ip地址
        parameters.put("notify_url", HttpClientWX.NOTIFYURL);// 微信回调地址返回信息地址
        parameters.put("trade_type", HttpClientWX.TRADETYPE);// 交易类型
        String sign = HttpClientWX.createSign(parameters);// 生成sign签名
        parameters.put("sign", sign);
        String requestXML = HttpClientWX.getRequestXml(parameters);
        Map<String, String> map = HttpClientInvokeWX.resolveXmlString(requestXML);
        System.out.println(requestXML);
    }

}
