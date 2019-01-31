package com.hydee.hdsec.entity.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by King.Liu
 * 2016/10/9.
 */
public class StringUtil {
    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {
        String _unicode = unicode.toString();
        String _regEx = "(\\\\u|\\\\U)[0-9a-zA-Z]{4}";
        Pattern _pat = Pattern.compile(_regEx);
        Matcher _mat = _pat.matcher(unicode);
        while(_mat.find()){
            String _key = _mat.group();
            // 转换出每一个代码点
            int word = Integer.parseInt(_key.substring(2, 6), 16);
            String wordStr = String.valueOf((char) word);
            // 追加成string
            _unicode = _unicode.replace(_key,wordStr);
        }
        return _unicode;
    }
}
