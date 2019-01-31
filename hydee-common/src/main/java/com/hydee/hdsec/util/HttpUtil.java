package com.hydee.hdsec.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by King.Liu
 * 2016/9/2.
 */
public class HttpUtil {
    private static HttpClient client = null;

    public static String IP;
    public static String PORT;
    public static String NAMESPACE;

    private static Properties prop;

    static {
        prop = new Properties();
        InputStream inputStream = null;
        BufferedReader bf = null;

        try {
            inputStream=HttpUtil.class.getResourceAsStream("/config.http.properties");
            bf = new BufferedReader(new InputStreamReader(inputStream));
            prop.load(bf);
            IP = prop.getProperty("http.ip");
            PORT = prop.getProperty("http.port");
            NAMESPACE = prop.getProperty("http.namespace");

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

    /**
     * 调用接口
     * @param space     对应接口
     * @param params    接口参数Map的形式存储
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static String getServiceName(String space, Map<String, Object> params) throws HttpException, IOException {
        String URL_ASSIGN = " http://"+IP+":"+PORT+"/"+NAMESPACE+"/"+space;
        if (client == null) {
            client = initHttpClient();
        }
        PostMethod postMethod = new PostMethod(URL_ASSIGN);
        for (String key: params.keySet()) {
            Object _value = params.get(key);
            if(_value != null){
                postMethod.addParameter(key, _value.toString());
            }
        }

        postMethod.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=UTF-8");
        client.executeMethod(postMethod);
        InputStream inputStream = postMethod.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        StringBuffer stringBuffer = new StringBuffer();
        String responsess = "";
        String str= "";
        while((str = br.readLine()) != null){
            stringBuffer .append(str);
        }
        responsess = stringBuffer.toString();
        br.close();
        inputStream.close();
        return URLDecoder.decode(responsess,"UTF-8");
    }

    public static String getServiceName(String space) throws HttpException, IOException {
        String URL_ASSIGN = " http://"+IP+":"+PORT+"/"+NAMESPACE+"/"+space;
        if (client == null) {
            client = initHttpClient();
        }
        PostMethod postMethod = new PostMethod(URL_ASSIGN);
        postMethod.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=UTF-8");
        client.executeMethod(postMethod);
        InputStream inputStream = postMethod.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        StringBuffer stringBuffer = new StringBuffer();
        String responsess = "";
        String str= "";
        while((str = br.readLine()) != null){
            stringBuffer .append(str);
        }
        responsess = stringBuffer.toString();
        br.close();
        inputStream.close();
        return URLDecoder.decode(responsess,"UTF-8");
    }

    /**
     * 控制httpclient连接数
     *
     * @return
     */
    private static HttpClient initHttpClient() {
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        // 指定向每个host发起的最大连接数，默认是2，太少了
        params.setDefaultMaxConnectionsPerHost(1000);
        // 指定总共发起的最大连接数，默认是20，太少了
        params.setMaxTotalConnections(5000);
        // 连接超时时间-10s
        params.setConnectionTimeout(60 * 1000);
        // 读取数据超时时间-60s
        params.setSoTimeout(60 * 1000);
        MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        manager.setParams(params);
        return new HttpClient(manager);
    }

    /**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return 返回微信服务器响应的信息
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            //URL url = new URL(requestUrl);

            /**
             * 重点在这里，需要使用带有URLStreamHandler参数的URL构造方法
             * update by xianggx 解决weblogic报错问题
             */
            URL url = new URL(null,requestUrl,new sun.net.www.protocol.https.Handler());

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            Logger.error("连接超时：{}", ce);
        } catch (Exception e) {
            Logger.error("https请求异常：{}", e);
        }
        return null;
    }

    public static void main(String[] args) throws HttpException, IOException {
        String space ="checkIn/leaveOrRest";
//        String space ="checkIn/checkInForGoToWorkOrOffWork";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", "9981");
        params.put("userName", "栾静安");
        params.put("customerId", "C94E40AA-68BB-47B3-816B-8FB4C0144DE7");
        params.put("os", "A261D233-E27B-4926-8029-965E883BCFEA-iPhone 6 Plus");
        params.put("remark", "xxx测试2");
        params.put("status", 1);
        params.put("classDetailId", 20);
        params.put("checkInDetailId", null);
        params.put("goToWorkAddress", "测试签到地址");
        params.put("goOffWorkAddress", "测试签退地址");
        params.put("signInTime", "18:00");
        params.put("signOutTime", "21:00");
        params.put("checkInId", "36263055466a4a05b487f5513c69d5cf");
        params.put("classDetailType", 1);
        String result = getServiceName(space,params);
        System.out.println(result);
    }
}
