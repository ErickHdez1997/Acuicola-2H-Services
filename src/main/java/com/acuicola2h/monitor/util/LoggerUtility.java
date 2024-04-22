package com.acuicola2h.monitor.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggerUtility {

	private static Logger logger = LogManager.getLogger(LoggerUtility.class);
	
	public static void printLog(Level level, String message) {
		if (level == Level.TRACE) {
			logger.trace(message);
		} else if (level == Level.DEBUG) {
			logger.debug(message);
		} else if (level == Level.INFO) {
			logger.info(message);
		} else if (level == Level.WARN) {
			logger.warn(message);
		} else if (level == Level.ERROR) {
			logger.error(message);
		} else if (level == Level.FATAL) {
			logger.fatal(message);
		}
	}
	
}
