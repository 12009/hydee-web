package com.hydee.hdsec.util;

import org.apache.log4j.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by King.Liu
 * 2016/8/30.
 */
public class LimitUtil {
    public static String exercisesLimitCount;

    public static String TASKLOCKTIME;

    private static Properties prop;
    static {
        prop = new Properties();
        InputStream inputStream = null;
        BufferedReader bf = null;

        try {
            inputStream=LimitUtil.class.getResourceAsStream("/config.limit.properties");
            bf = new BufferedReader(new InputStreamReader(inputStream));
            prop.load(bf);

            exercisesLimitCount = prop.getProperty("exercises.limit.count");
            TASKLOCKTIME = prop.getProperty("task.lock.time");
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

    public static void main(String[] args) {
        System.out.println(exercisesLimitCount);
    }
}
