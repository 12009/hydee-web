package com.hydee.hdsec.entity.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Created by King.Liu
 * 2016/8/29.
 */
public class PageUtil {
    protected static Logger logger = Logger.getLogger(PageUtil.class);
    public static String pageSize;

    private static Properties prop;
    static {
        prop = new Properties();
        InputStream inputStream = null;
        BufferedReader bf = null;

        try {
            inputStream=PageUtil.class.getResourceAsStream("/config.page.properties");
            bf = new BufferedReader(new InputStreamReader(inputStream));
            prop.load(bf);

            pageSize = prop.getProperty("page.size");
        } catch (Exception e) {
            logger.error("分页读取配置出错",e);
        } finally {
            try {
                inputStream.close();
                bf.close();
            } catch (IOException ex) {
                logger.error("分页读取配置出错",ex);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(PageUtil.pageSize);
    }

}
