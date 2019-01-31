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
        if (unicode == null) return null;
        String _unicode = unicode.toString().replaceAll("\\\\\\\\", "\\\\");
        String _regEx = "\\\\(u|U)[0-9a-zA-Z]{2,4}";
        Pattern _pat = Pattern.compile(_regEx);
        Matcher _mat = _pat.matcher(unicode);
        while (_mat.find()) {
            String _key = _mat.group();
            // 转换出每一个代码点
            int word = Integer.parseInt(_key.substring(2, _key.length()), 16);
            String wordStr = String.valueOf((char) word);
            // 追加成string
            _unicode = _unicode.replace(_key, wordStr);
        }
        return _unicode;
    }

    public static String string2UnicodeEx(String source) {
        if (source == null) return null;
        String returnUniCode = null;
        String uniCodeTemp = null;
        for (int i = 0; i < source.length(); i++) {
            uniCodeTemp = "\\u" + Integer.toHexString((int) source.charAt(i));
            returnUniCode = returnUniCode == null ? uniCodeTemp : returnUniCode
                    + uniCodeTemp;
        }
        return returnUniCode;
    }

    public static void main(String[] args) {
        System.out.println(unicode2String("\\u49\\u40\\u50\\u38\\u39\\u4e\\u4a\\u3c\\u2e\\u57\\u58\\u40\\u32\\u3d\\u48\\u44\\u4f\\u46\\u51\\u48\\u44\\u40\\u55\\u4c\\u4d\\u58\\u63\\u4b"));
    }
}
