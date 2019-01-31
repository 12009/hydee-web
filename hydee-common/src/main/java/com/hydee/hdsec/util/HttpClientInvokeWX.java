package com.hydee.hdsec.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HttpClientInvokeWX {
	// 请求客户端配置
	private static CloseableHttpClient httpclient;
	// 微信接口红包支付接口地址
	private static String porstUrl;
	// 接口签名生成所需key
	private static String signKey;
	// 接口支付静态参数配置
	private static Map<String,Object> params = new TreeMap<String, Object>();
	// 流水号
	private static long number = System.currentTimeMillis() / 1000L;
	
	private static Properties prop;
	
	/**
	 * 初始化接口通信配置
	 */
    static {
        prop = new Properties();
        InputStream inputStream = null;
        BufferedReader bf = null;

        try {
            inputStream = HttpClientInvokeWX.class.getResourceAsStream("/weixin.config.properties");
            bf = new BufferedReader(new InputStreamReader(inputStream));
            prop.load(bf);
            Iterator<Entry<Object, Object>> it = prop.entrySet().iterator();  
            while (it.hasNext()) {  
                Entry<Object, Object> entry = it.next();  
                String key = entry.getKey().toString();  
                Object value = entry.getValue();  
                if( key.equals("porstUrl") ){
                	porstUrl = value.toString();
                }else if( key.equals("signKey")){
                	signKey  = value.toString();
                }else{
                	params.put(key, value);
                }
            }
        } catch (Exception e) {
            Logger.error(e);
        } finally {
            try {
            	bf.close();
                inputStream.close();
            } catch (IOException ex) {
                Logger.error(ex);
            }
        }
    }
    /**
     * 发送红包(重新生成流水号)
     * @param openid		:接受红包的用户在wxappid下的openid
     * @param amount		:红包金额(单位:元[1-200元])
     * @return
     * @throws Exception 
     */
    public static Map<String, String> sendRedpack(String openid,double amount) throws Exception{
        return sendRedpack(openid, amount, getMchBillno());
    }
    /**
     * 发送红包
     * @param openid		:接受红包的用户在wxappid下的openid
     * @param amount		:红包金额(单位:元[1-200元])
     * @param mchBillno		:流水号
     * @return
     * @throws Exception
     */
    public static Map<String, String> sendRedpack(String openid,double amount,String mchBillno) throws Exception{
    	// 追加nonce_str参数
        params.put("nonce_str", getNonceStr());
        // 追加订单号参数
        params.put("mch_billno", mchBillno);
        // 追加客户openid
        params.put("re_openid", openid);
        // 追加金额参数
        params.put("total_amount", new Double(amount * 100).intValue());
    	// 构建请求用参数
        String reuqestXml = buildRequestData();
        // 请求接口
        String responseXml = doPost(reuqestXml);
        
        return resolveXmlString(responseXml);
    }
    /**
     * 获取流水号
     * @return
     */
    public static String getMchBillno() {
    	String mch_id = params.get("mch_id").toString();
    	String date = DateUtil.getDays();
    	String randNum = String.format("%010d", number++);
    	return mch_id + date + randNum;
    }
    
	/**
     * 向微信红包接口发送请求
     * @param reuqestXml
     * @return
     * @throws Exception
     */
	private static String doPost(String reuqestXml) throws Exception{  
        return doPost(porstUrl,reuqestXml);
    }

    public static String doPost(String url, String reuqestXml) throws Exception{
        String rspStr = "";
        try {
            initHttpClient();
            HttpPost httpPost = new HttpPost(url);// 退款接口
            httpPost.addHeader("Content-Type", "text/xml; charset=UTF-8");
            // 防止微信签名错误,需要指定字符编码为ISO8859-1
            StringEntity reqEntity = new StringEntity( new String(reuqestXml.getBytes("UTF-8"), "ISO8859-1") );
            // 设置类型
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                org.apache.http.HttpEntity entity = response.getEntity();
                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(entity.getContent(), "UTF-8"));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        rspStr += text;
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return rspStr;
    }
    /**
     * 初始化HttpClient
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
	private static void initHttpClient() throws Exception {
    	KeyStore keyStore = KeyStore.getInstance("PKCS12");  
    	InputStream instream = HttpClientInvokeWX.class.getResourceAsStream("/apiclient_cert.p12");  
    	try {  
    		char[] _password = prop.getProperty("mch_id").toCharArray();
    		keyStore.load(instream, _password);  
    		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, _password).build();  
    		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
    				sslcontext, new String[] { "TLSv1" }, null,
    				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);  
    		httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();  
    	} finally {  
    		instream.close();  
    	}  
    }
    /**
     * 构建请求xml内容
     * @return
     * @throws UnsupportedEncodingException 
     */
	private static String buildRequestData() throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer("<xml>");
		sb.append("<sign><![CDATA["+getSign()+"]]></sign>");
		for (String key : params.keySet()) {
			sb.append("<"+key+"><![CDATA[" + params.get(key).toString() + "]]></"+key+">");
		}
		// 追加签名参数
		sb.append("</xml>");
		return sb.toString();
	}
    /**
     * 生成签名
     * @return
     * @throws UnsupportedEncodingException 
     */
    private static String getSign() throws UnsupportedEncodingException{
    	StringBuffer sign = new StringBuffer();
    	for (String _key : params.keySet()) {
    		sign.append(_key + "=" + params.get(_key) + "&");
		}
    	sign.append("key=" + signKey);
    	return MD5.getMD5Format(sign.toString().getBytes("UTF-8")).toUpperCase();
    }
 	/**
 	 * 生成32位随机码
 	 * @return
 	 */
    private static String getNonceStr(){
    	return UUID.randomUUID().toString().replace("-", "");
    }
    /**
     * 解析XML
     * @param reqXml
     * @return
     */
    public static Map<String,String> resolveXmlString(String reqXml){
		Map<String,String> result = new HashMap<String, String>();
		
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(new ByteArrayInputStream(reqXml.getBytes("UTF-8")));
            Element incomingForm = document.getRootElement();
            Iterator<Element> itr = incomingForm.elementIterator();
            while (itr.hasNext()) {
				Element element = itr.next();
				result.put(element.getName(), element.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
    
    /**
     * 单元测试
     * @param args
     */
    public static void main(String[] args) {
		try {
			Map<String, String> map = sendRedpack("oku3Jwk-9d-mhQLoTHdOciqz1Zfo", 100.0);
			for (String key : map.keySet()) {
				System.out.println(key + " : " + map.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
