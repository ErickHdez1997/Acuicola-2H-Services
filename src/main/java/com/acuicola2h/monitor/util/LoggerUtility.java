package com.acuicola2h.monitor.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggerUtility {
	
	private LoggerUtility() {}

	private static Logger logger = null;
	
	public static void log(Class<?> clazz, Level level, Object ...obj) {
		logger = LogManager.getLogger(clazz);
		if (level == Level.TRACE) {
			logger.trace(obj);
		} else if (level == Level.DEBUG) {
			logger.debug(obj);
		} else if (level == Level.INFO) {
			logger.info(obj);
		} else if (level == Level.WARN) {
			logger.warn(obj);
		} else if (level == Level.ERROR) {
			logger.error(obj);
		} else if (level == Level.FATAL) {
			logger.fatal(obj);
		}
	}
	
}
