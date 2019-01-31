package com.hydee.hdsec.util;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 */
public class MD5 {
	
	/**
	 * 使用二次加密来强化MD5加密
	 * @param str		:待加密字串
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 128;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}

	public static String encode(String inPassword){
		String outPassword = "";
		String returnVal = "";
		if(!StringUtil.isBlank(inPassword)){
			for(int i = 1, j = inPassword.length(); i <= j ; i++){
				String mid = inPassword.substring(i-1, i);
				int asc = mid.charAt(0);
				String str = (100 + asc) * 23 + "";
				outPassword = outPassword + str;
			}
			String tempStr ="";
			for(int i=0, j=outPassword.length();i<j;i++){
				tempStr += outPassword.charAt(outPassword.length()-i-1);
			}
			outPassword = tempStr;
			for(int i = 1, j=outPassword.length(); i <= j ; i++){
				String mid = outPassword.substring(i-1,i);
				returnVal = returnVal + (char) (32+ (Float.parseFloat(mid) * 5) + i);
			}
		}
		// 解决sql语句中传递的值包括 单引号时 sql 语句报错的情况
		returnVal = returnVal.replace("'", "''");
		return returnVal;
	}

	public static String getZllnKey(){
		String _key = Long.toHexString(System.currentTimeMillis());
		System.out.println(_key);
		return _key + encode(_key);
	}
	
	/**
	 * 单元测试
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(md5("admin123"));
		System.out.println(md5("123456"));
	}
}
