package com.ajie.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ajie.api.weixin.impl.WeixinApiImpl;

/**
 * api日志辅助类
 *
 * @author niezhenjie
 *
 */
public class ApiLog {
	public static final Logger logger = LoggerFactory.getLogger(WeixinApiImpl.class);
	/** 日志级别 -- info */
	public static final int LEVEL_INFO = 1;
	/** 日志级别 -- warn */
	public static final int LEVEL_WARN = 2;
	/** 日志级别 -- error */
	public static final int LEVEL_ERROR = 3;
	/** 日志级别 -- debug */
	public static final int LEVEL_DEBUG = 4;
	/** 日志级别 -- trace */
	public static final int LEVEL_TRACE = 5;

	/**
	 * 打印日志，指定日志级别，指定使用的logger实例
	 * 
	 * @param method
	 *            方法名
	 * @param url
	 *            调用链接
	 * @param ret
	 *            调用结果
	 * @param level
	 *            日志级别
	 * @param logger
	 *            日志实例
	 */
	public static void log(String method, String url, String ret, int level, Logger logger) {
		if (null == logger) {
			logger = ApiLog.logger;
		}
		String log = wrapLog(method, url, ret);
		if (level == LEVEL_INFO) {
			logger.info(log);
		} else if (level == LEVEL_WARN) {
			logger.warn(log);
		} else if (level == LEVEL_ERROR) {
			logger.error(log);
		} else if (level == LEVEL_DEBUG) {
			logger.debug(log);
		} else if (level == LEVEL_TRACE) {
			logger.debug(log);
		}
	}

	/**
	 * 打印日志，指定日志级别，使用ApiLog实例日志
	 * 
	 * @param method
	 *            方法名
	 * @param url
	 *            调用链接
	 * @param ret
	 *            调用结果
	 * @param level
	 *            日志级别
	 */
	public static void log(String method, String url, String ret, int level) {
		log(method, url, ret, level, logger);
	}

	/**
	 * 打印日志，指定日志级别，使用指定实例日志
	 * 
	 * @param method
	 *            方法名
	 * @param url
	 *            调用链接
	 * @param ret
	 *            调用结果
	 * @param level
	 *            日志级别
	 * @param clazz
	 *            日志实例
	 */
	public static <T> void log(String method, String url, String ret, int level, Class<T> clazz) {
		log(method, url, ret, level, LoggerFactory.getLogger(clazz));
	}

	/**
	 * 打印日志，指定日志级别，使用指定实例日志
	 * 
	 * @param method
	 *            方法名
	 * @param url
	 *            调用链接
	 * @param ret
	 *            调用结果
	 * @param level
	 *            日志级别
	 * @param instance
	 *            日志实例
	 */
	public static <T> void log(String method, String url, String ret, int level, Object instance) {
		log(method, url, ret, level, LoggerFactory.getLogger(instance.getClass()));
	}

	/**
	 * 使用ApiLog日志实例打印一条info日志
	 * 
	 * @param method
	 * @param url
	 * @param ret
	 */
	public static <T> void log(String method, String url, String ret) {
		log(method, url, ret, LEVEL_INFO, logger);
	}

	public static void trace(String method, String url, String ret, Object instance) {
		log(method, url, ret, LEVEL_TRACE, instance);
	}

	private static String wrapLog(String method, String url, String ret) {
		StringBuilder sb = new StringBuilder();
		sb.append("{method: ");
		sb.append(method);
		sb.append(",url: ");
		sb.append(url);
		sb.append(",result: ");
		sb.append(ret);
		sb.append("}");
		return sb.toString();
	}

}
