package com.hydee.hdsec.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class ServerChargeUtil {

    public static String SERVERCHARGE;

    public static String SERVERCHARGEPERCENT;

    private static Properties prop;
    static {
        prop = new Properties();
        InputStream inputStream = null;
        BufferedReader bf = null;

        try {
            inputStream = ServerChargeUtil.class.getResourceAsStream("/config.serverCharge.properties");
            bf = new BufferedReader(new InputStreamReader(inputStream));
            prop.load(bf);

            SERVERCHARGE = prop.getProperty("task.serverCharge");
            SERVERCHARGEPERCENT = prop.getProperty("task.serverCharge.percent");
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
}
