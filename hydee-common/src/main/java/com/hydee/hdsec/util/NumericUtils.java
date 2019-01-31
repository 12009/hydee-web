package com.hydee.hdsec.util;

import java.text.DecimalFormat;

public class NumericUtils {
	
    /**
     * 保留两位小数，四舍五入的一个老土的方法
     * DecimalFormat is a concrete subclass of NumberFormat that formats decimal numbers. 
     * @param d
     * @return
     */
    public static String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(d);
    }
    
    /**
     * 如果只是用于程序中的格式化数值然后输出，那么这个方法还是挺方便的。
     * 应该是这样使用：System.out.println(String.format("%.2f", d));
     * @param d
     * @return
     */
    public static String formatDoubleEx(double d) {
        return String.format("%.2f", d);
    }
    
}
