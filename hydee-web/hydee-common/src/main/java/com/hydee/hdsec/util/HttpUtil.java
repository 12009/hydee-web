package com.hydee.hdsec.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
}
