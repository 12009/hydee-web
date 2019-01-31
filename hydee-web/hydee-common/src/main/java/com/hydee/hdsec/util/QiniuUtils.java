package com.hydee.hdsec.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiniuUtils {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	private static String accessKey;
	private static String secretKey;
	// 七牛云域名空间
	private static String sourceUrl;
	// 要上传的空间
	private static String bucketName;
	private static Auth auth;
	private static UploadManager uploadManager;
	
	static{
		Properties prop = new Properties();  
		InputStream inputStream = null;
		BufferedReader bf = null;
		try {
			inputStream = EmailUtils.class.getResourceAsStream("/config.qiniu.properties"); 
			bf = new BufferedReader(new InputStreamReader(inputStream)); 
			prop.load(bf);  
			
			accessKey = prop.getProperty("qiniu.access.key");
			secretKey = prop.getProperty("qiniu.secret.key");
			sourceUrl = prop.getProperty("qiniu.source.url");
			bucketName = prop.getProperty("qiniu.bucket.name");
		} catch (Exception e) {
			Logger.error(e);
		} finally{
			try {
				inputStream.close();
				bf.close();
			} catch (IOException ex) {
				Logger.error(ex);
			}
		}
		
		// 密钥配置
		auth = Auth.create(accessKey, secretKey);
		// 创建上传对象
		uploadManager = new UploadManager();
	}

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken() {
		return auth.uploadToken(bucketName);
	}
	/**
	 * 上传文件到七牛云
	 * @param filePath		:上传的文件路径
	 * @param key			:上传的文件名
	 * @throws IOException
	 */
	public static void upload(String filePath,String key) throws IOException {
		try {
			// 调用put方法上传
			Response res = uploadManager.put(filePath, key, getUpToken());
			// 打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}
	/**
	 * 删除七牛云文件
	 * @param filePath		:待删除文件访问地址(含七牛云下载域名空间的全路径地址)
	 */
	public static void delete(String filePath) throws QiniuException{
		int index = StringUtil.isBlank(filePath) ? -1 : filePath.indexOf(sourceUrl);
		if(index < 0) return;
		String key = filePath.substring(index + sourceUrl.length());
		//设置需要操作账号的AK和SK
	    Auth auth = Auth.create(accessKey, secretKey);
	    //实例化一个BucketManager对象
	    BucketManager bucketManager = new BucketManager(auth);
	    //要测试的空间和key，并且这个key在你空间中存在
	    try {
	      //调用delete方法移动文件
	      bucketManager.delete(bucketName, key);
	    } catch (QiniuException e) {
	      //捕获异常信息
	      Response r = e.response;
	      System.out.println(r.toString());
	    }
	}
}
