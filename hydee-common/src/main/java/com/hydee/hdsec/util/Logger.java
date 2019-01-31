package com.hydee.hdsec.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

public class Logger {
	private static Log logger;
	
	static{
		logger = LogFactoryImpl.getLog(Logger.class);
	}
	/**
	 * 打印错误日志(附带堆栈信息)
	 * @param info
	 */
	public static void error(Throwable info) {
		logger.error("",info);
	}
	/**
	 * 打印错误日志(附带堆栈信息)
	 * @param info
	 */
	public static void error(String msg,Throwable info) {
		logger.error(msg,info);
	}
	/**
	 * 打印错误日志(仅错误内容)
	 * @param err
	 */
	public static void error(Object err) {
		logger.error(err);
	}
	/**
	 * 打印info级别日志
	 * @param info
	 */
	public static void info(Object info) {
		logger.info(info);
	}
	/**
	 * 打印debug级别日志
	 * @param info
	 */
	public static void debug(Object info) {
		logger.debug(info);
	}
	/**
	 * 打印warn级别日志
	 * @param info
	 */
	public static void warn(Object info) {
		logger.debug(info);
	}
}
