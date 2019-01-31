package com.hydee.hdsec.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

import com.hydee.hdsec.entity.IPAddress;

import javax.servlet.http.HttpServletRequest;

public class HttpClientUtils {
	
	/**
	 * 获取IP对应的物理地址
	 * @param ip	：ip地址
	 * @return		：ip物理地址JSON字串{}
	 */
	public static IPAddress requestIpAddr(String ip) {
		InputStream instr = null;
		IPAddress ipAddress = new IPAddress();
		try {
			// 登录用户信息
			URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip="
					+ ip + "&qq-pf-to=pcqq.c2c");
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			urlCon.setUseCaches(false);
			// 超时设置
			urlCon.setConnectTimeout(1000 * 30);
			// 设置消息头
			urlCon.addRequestProperty("User-Agent",
					"Mozilla/4.0(compatible;MSIE5.5;Windows NT; DigExt)");
			urlCon.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			urlCon.setRequestProperty("Accept-Encoding", "utf-8");
			urlCon.setRequestMethod("GET");
			DataOutputStream printout = new DataOutputStream(
					urlCon.getOutputStream());
			// 请求接口
			// 推送请求数据
			printout.flush();
			// 获取响应消息并关闭连接
			printout.close();
			if (urlCon.getResponseCode() == 500) {
				instr = urlCon.getErrorStream();
			} else {
				// 处理gzip类型返回的数据
				String encoding = urlCon.getContentEncoding();
				if (encoding != null && encoding.toLowerCase().equals("gzip")) {
					instr = new GZIPInputStream(urlCon.getInputStream());
				} else {
					instr = urlCon.getInputStream();
				}
			}
			byte[] bis = IOUtils.toByteArray(instr);
			String ResponseString = new String(bis, "UTF-8");
			if ((ResponseString == null) || ("".equals(ResponseString.trim()))) {
				return null;
			}
			JSONObject result = JSONObject.fromObject(ResponseString).getJSONObject("data");
			String ipAddrCountry = result.getString("country");
			String ipAddrArea = result.getString("area");
			String ipAddrRegion = result.getString("region");
			String ipAddrCity = result.getString("city");
			ipAddress.setIp(ip);
			ipAddress.setCountry(ipAddrCountry);
			ipAddress.setArea(ipAddrArea);
			ipAddress.setRegion(ipAddrRegion);
			ipAddress.setCity(ipAddrCity);
			return ipAddress;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				instr.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
	}

	public static String getReturnStream(HttpServletRequest request) throws IOException {
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		Logger.info(buffer);
		outSteam.close();
		inStream.close();
		String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
		return result;
	}
}
