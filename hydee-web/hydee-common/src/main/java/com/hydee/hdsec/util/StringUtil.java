package com.hydee.hdsec.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 项目名称：hdsec
 * 类名称：StringUtil
 * 类描述：字符串工具包
 * 修改备注：
 *
 * @version Ver 1.1
 */
public class StringUtil {

	/**
	 * isBlank(判断字符串是否为空)
	 */
	public static boolean isBlank(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
	/**
	 * 判断多个字符串是否为空
	 * @param allCheck	:是否为且判断(true:且判断,false:或判断)
	 * @param strs		:传入的字符串
	 * @return
	 */
	public static boolean isBlanks(boolean allCheck,String... strs){
		for(String str : strs){
			if(isBlank(str) && allCheck){
				return true;
			}else if(!isBlank(str) && !allCheck){
				return false;
			}
		}
		return !allCheck;
	}
	/**
	 * 获取验证码
	 * @param count		:验证码位数
	 * @return
	 */
	public static String getVerifiCode(int count){
		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		for(int i=0;i<count;i++){
			sb.append(String.valueOf(rand.nextInt(10)));
		}
		return sb.toString();
	}

	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {
		if(unicode == null) return null;
		String _unicode = unicode.toString();
		String _regEx = "(\\\\u|\\\\U)[0-9a-zA-Z]{2,4}";
		Pattern _pat = Pattern.compile(_regEx);
		Matcher _mat = _pat.matcher(unicode);
		while(_mat.find()){
			String _key = _mat.group();
			// 转换出每一个代码点
			int word = Integer.parseInt(_key.substring(2, _key.length()), 16);
			String wordStr = String.valueOf((char) word);
			// 追加成string
			_unicode = _unicode.replace(_key,wordStr);
		}
		return _unicode;
	}
}
