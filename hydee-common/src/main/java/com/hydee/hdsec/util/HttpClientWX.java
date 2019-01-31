package com.hydee.hdsec.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class HttpClientWX {
	public static String MCHID;
	public static String WXAPPID;
	public static String UNIFIEDORDERURL;//统一下单
	public static String NOTIFYURL;//回调接口
	public static String ORDERQUERY;//查询订单接口
	public static String KEY;//加密唯一键
	public static String TRADETYPE;//交易类型
	public static String REFUND;//退款
	private static Properties prop;
	
	/**
	 * 初始化接口通信配置
	 */
    static {
		prop = new Properties();
		InputStream inputStream = null;
		BufferedReader bf = null;

		try {
			inputStream=HttpUtil.class.getResourceAsStream("/config.weixin.pay.properties");
			bf = new BufferedReader(new InputStreamReader(inputStream));
			prop.load(bf);
			MCHID = prop.getProperty("weixin.mch_id");
			WXAPPID = prop.getProperty("weixin.wxappid");
			UNIFIEDORDERURL = prop.getProperty("weixin.unifiedorderurl");
			NOTIFYURL = prop.getProperty("weixin.notifyurl");
			ORDERQUERY = prop.getProperty("weixin.orderquery");
			KEY = prop.getProperty("weixin.key");
			TRADETYPE = prop.getProperty("weixin.tradetype");
			REFUND = prop.getProperty("weixin.refund");

		} catch (Exception e) {
			Logger.error(e);
		} finally {
			try {
				inputStream.close();
				bf.close();
			} catch (IOException ex) {
				Logger.error(ex);
			}
		}
    }

	public static String createSign(SortedMap<Object, Object> parameters) throws Exception {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v)
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=").append(KEY);
		String sign = MD5.getMD5Format(sb.toString().getBytes("UTF-8")).toUpperCase();
		return sign;
	}

	public static String createSign2(SortedMap<Object, Object> parameters){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v)
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
//		sb.append("key=y4AkWdwUKzldAradNqIyucK2IWSqvYS0");
		return sb.toString();
	}
 	/**
 	 * 生成32位随机码
 	 * @return
 	 */
    public static String getNonceStr(){
    	return UUID.randomUUID().toString().replace("-", "");
    }

	/**
	 * 获取真实IP地址
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 * @return String
	 */
	public final static String getIpAddress(HttpServletRequest request) {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
		try {
			String ip = request.getHeader("X-Forwarded-For");
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("WL-Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("HTTP_CLIENT_IP");
				}
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				}
				if (ip == null || ip.length() == 0
						|| "unknown".equalsIgnoreCase(ip)) {
					ip = request.getRemoteAddr();
				}
			} else if (ip.length() > 15) {
				String[] ips = ip.split(",");
				for (int index = 0; index < ips.length; index++) {
					String strIp = (String) ips[index];
					if (!("unknown".equalsIgnoreCase(strIp))) {
						ip = strIp;
						break;
					}
				}
			}
			return ip;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters  请求参数
	 * @return
	 */
	public static String getRequestXml(SortedMap<Object,Object> parameters){
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
				sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
			}else {
				sb.append("<"+k+">"+v+"</"+k+">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
	/**
	 * @Description：返回给微信的参数
	 * @param return_code 返回编码
	 * @param return_msg  返回信息
	 * @return
	 */
	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code
				+ "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}


	/**
	 * 拿到微信端返回的url生成二维码
	 *
	 * @param @param url
	 * @param @throws WriterException
	 * @param @throws IOException
	 * @return void
	 */
	public static void createdImage(String url, HttpServletResponse response)
			throws WriterException, IOException {
		String createdUrl = url;
		if (url == null || "".equals(url)) {
		}
		try {
			int width = 300;
			int height = 300;
			// 二维码的图片格式
			String format = "gif";
			Hashtable hints = new Hashtable();
			// 内容所使用编码
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(createdUrl,
					BarcodeFormat.QR_CODE, width, height, hints);
			// 生成二维码
			MatrixToImageWriter.writeToJsp(bitMatrix, response);
			// File outputFile = new File("d:"+File.separator+"new.gif");
			// MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 统一下单
	 * @param outTradeNo
	 * @param totalFee
	 * @param request
     * @return
     */
	public static String unifiedOrder(String outTradeNo,String totalFee,HttpServletRequest request) throws Exception {
		// 构造参数调用统一下单接口
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WXAPPID);// 公众账号ID
		parameters.put("mch_id", MCHID);// 商户号
		parameters.put("nonce_str", getNonceStr());// 随机字符串
		parameters.put("body", "厂家培训-充值");
		parameters.put("out_trade_no", outTradeNo);// 商户订单号
		parameters.put("total_fee", totalFee);// 商品金额,以分为单位
		parameters.put("spbill_create_ip", getIpAddress(request));// ip地址
		parameters.put("notify_url", NOTIFYURL);// 微信回调地址返回信息地址
		parameters.put("trade_type", TRADETYPE);// 交易类型
		String sign = createSign(parameters);// 生成sign签名
		parameters.put("sign", sign);
		String requestXML = getRequestXml(parameters);
		String result = HttpClientInvokeWX.doPost(UNIFIEDORDERURL,requestXML);// 调用微信支付统一接口
		return result;
	}

	/**
	 * 查询订单
	 * @param outTradeNo
	 * @return
     */
	public static String orderQuery(String outTradeNo) throws Exception {
		// 构造参数调用查询订单接口
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WXAPPID);// 公众账号ID
		parameters.put("mch_id", MCHID);// 商户号
		parameters.put("nonce_str", getNonceStr());// 随机字符串
		parameters.put("out_trade_no", outTradeNo);// 商户订单号
		String sign = createSign(parameters);// 生成sign签名
		parameters.put("sign", sign);
		String requestXML = getRequestXml(parameters);
		String result = HttpUtil.httpsRequest(ORDERQUERY, "POST", requestXML);// 调用微信支付查询订单接口
		return result;
	}

	/**
	 * 退款
	 * @param outTradeNo
	 * @param _totalFee
	 * @return
	 * @throws Exception
     */
	public static String reFund(String outTradeNo,String _totalFee) throws Exception {
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WXAPPID);// 公众账号ID
		parameters.put("mch_id", MCHID);// 商户号
		parameters.put("nonce_str", getNonceStr());// 随机字符串
		parameters.put("out_trade_no", outTradeNo);// 商户订单号
		parameters.put("out_refund_no", outTradeNo);// 商户订单号
		parameters.put("total_fee", _totalFee);// 商品金额,以分为单位
		parameters.put("refund_fee", _totalFee);// 退还金额,以分为单位
		parameters.put("op_user_id", MCHID);// 商户号
		String sign = createSign(parameters);// 生成sign签名
		parameters.put("sign", sign);
		String requestXML = getRequestXml(parameters);
		String result = HttpClientInvokeWX.doPost(REFUND,requestXML);
		return result;
	}

}
