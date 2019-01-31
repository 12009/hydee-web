package com.hydee.hdsec.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.omg.IOP.Encoding;

/**
 * MD5加密工具类
 */
public class MD5 {
	
	private static ThreadLocal<MessageDigest> messageDigestHolder = new ThreadLocal();
	
	static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	static {
		try {
			MessageDigest message = MessageDigest.getInstance("MD5");
			messageDigestHolder.set(message);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("初始化java.security.MessageDigest失败:", e);
		}
	}

	/**
	 * 使用二次加密来强化MD5加密(不可逆)
	 * @param str	:待加密字串
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
	
	/**
	 * 获取zllnKey
	 * @return
	 */
	public static String getZllnKey() {
		String _key = Long.toHexString(System.currentTimeMillis());
		return _key + encode(_key);
	}
	
	/**
	 * MD5加密字符串
	 * @param data
	 * @return
	 */
	public static String getMD5Format(String data) {
		try {
			MessageDigest message = (MessageDigest) messageDigestHolder.get();
			if (message == null) {
				message = MessageDigest.getInstance("MD5");
				messageDigestHolder.set(message);
			}
			message.update(data.getBytes());
			byte[] b = message.digest();

			String digestHexStr = "";
			for (int i = 0; i < 16; ++i) {
				digestHexStr = digestHexStr + byteHEX(b[i]);
			}

			return digestHexStr;
		} catch (Exception e) {
			throw new RuntimeException("MD5格式化时发生异常[{}]: {}", e);
		}
	}
	/**
	 * MD5加密字节码
	 * @param data
	 * @return
	 */
	public static String getMD5Format(byte[] data) {
		try {
			MessageDigest message = (MessageDigest) messageDigestHolder.get();
			if (message == null) {
				message = MessageDigest.getInstance("MD5");
				messageDigestHolder.set(message);
			}

			message.update(data);
			byte[] b = message.digest();

			String digestHexStr = "";
			for (int i = 0; i < 16; ++i) {
				digestHexStr = digestHexStr + byteHEX(b[i]);
			}

			return digestHexStr;
		} catch (Exception e) {
		}
		return null;
	}
	
	private static String byteHEX(byte ib) {
		char[] ob = new char[2];
		ob[0] = hexDigits[(ib >>> 4 & 0xF)];
		ob[1] = hexDigits[(ib & 0xF)];
		String s = new String(ob);
		return s;
	}

	
	/**
	 * H1字串加密
	 * @param inPassword
	 * @return
	 */
	private static String encode(String inPassword) {
		String outPassword = "";
		String returnVal = "";
		if (!StringUtil.isBlank(inPassword)) {
			for (int i = 1, j = inPassword.length(); i <= j; i++) {
				String mid = inPassword.substring(i - 1, i);
				int asc = mid.charAt(0);
				String str = (100 + asc) * 23 + "";
				outPassword = outPassword + str;
			}
			String tempStr = "";
			for (int i = 0, j = outPassword.length(); i < j; i++) {
				tempStr += outPassword.charAt(outPassword.length() - i - 1);
			}
			outPassword = tempStr;
			for (int i = 1, j = outPassword.length(); i <= j; i++) {
				String mid = outPassword.substring(i - 1, i);
				returnVal = returnVal
						+ (char) (32 + (Float.parseFloat(mid) * 5) + i);
			}
		}
		// 解决sql语句中传递的值包括 单引号时 sql 语句报错的情况
		returnVal = returnVal.replace("'", "''");
		return returnVal;
	}
}
