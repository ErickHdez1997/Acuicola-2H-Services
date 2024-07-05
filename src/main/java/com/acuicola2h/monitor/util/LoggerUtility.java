package com.acuicola2h.monitor.util;

import java.util.Arrays;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.acuicola2h.monitor.model.LogContext;

@Component
public class LoggerUtility extends BaseLogger {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtility.class);
	private static final String DEFAULT_LOG_LEVEL = "TRACE";
	
	public static final void print(Logger logger, String level, LogContext logContext, Object... obj) {
		try {
			logger = (logger == null) ? LOGGER : logger;
			level = (level == null) ? DEFAULT_LOG_LEVEL : level;
			StringBuilder log = new StringBuilder();
			log.append("methodName: ").append(logContext.getMethodName());
			if (logContext.getMessageId() != null && !logContext.getMessageId().isEmpty()) {
				log.append(", messageId: ").append(logContext.getMessageId());
			}
			if (logContext.getMessage() != null && !logContext.getMessage().isEmpty()) {
				log.append(", message: ").append(logContext.getMessage());
			}
			String cleanup = log.toString().replace('\n', ' ').replace('\r', ' ').replace('\f', ' ');
			String logString = String.format("%s",  cleanup);
			if (obj != null && !Arrays.toString(obj).isEmpty()) {
				logString += " - {}";
			}
			switch (level) {
			case DEFAULT_LOG_LEVEL:
				logger.trace(logString, obj);
				break;
			case "DEBUG":
				logger.debug(logString, obj);
				break;
			case "INFO":
				logger.info(logString, obj);
				break;
			case "WARN":
				logger.trace(logString, obj);
				break;
			case "ERROR":
				logger.trace(logString, obj);
				break;
			default:
				logger.debug(logString, obj);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
//			LoggerUtility.error(LoggerUtility.class, logContext, e);
		}
	}
	
	public static void error(Class<?> cls, LogContext logContext, Exception e) {
	    if (getClassLogger(cls).isErrorEnabled()) {
	        printExceptionLog(cls, Level.ERROR, logContext, e);
	    }
	}

	public static void printMethodStart(Class<?> cls, Level level, LogContext logContext) {
		logContext.setMessage(Constants.LOG_START);
	    print(getClassLogger(cls), level.toString(), logContext, "");
	}

	public static void printMethodEnd(Class<?> cls, Level level, LogContext logContext) {
		logContext.setMessage(Constants.LOG_END);
	    print(getClassLogger(cls), level.toString(), logContext, "");
	}

	public static void printRawLog(Class<?> cls, Level level, LogContext logContext, String e) {
	    print(getClassLogger(cls), level.toString(), logContext, e);
	}

	public static void printLog(Class<?> cls, Level level, LogContext logContext) {
	    print(getClassLogger(cls), level.toString(), logContext);
	}

	public static void printExceptionLog(Class<?> cls, Level level, LogContext logContext, Exception args) {
	    String error = ExceptionUtils.getStackTrace(args);
	    printRawLog(cls, level, logContext, error);
	}
	
}
