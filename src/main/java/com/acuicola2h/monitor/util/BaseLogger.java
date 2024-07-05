package com.acuicola2h.monitor.util;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseLogger {

    private static final HashMap<String, Logger> classLoggerMap = new HashMap<>(16);

    protected static Logger getClassLogger(Class<?> cls) {
        if (!classLoggerMap.containsKey(cls.getName()) || classLoggerMap.get(cls.getName()) == null) {
            classLoggerMap.put(cls.getName(), LoggerFactory.getLogger(cls));
        }
        return classLoggerMap.get(cls.getName());
    }

    protected static Logger getClassLogger(Object object) {
        if (!classLoggerMap.containsKey(object.getClass().getName()) || classLoggerMap.get(object.getClass().getName()) == null) {
            classLoggerMap.put(object.getClass().getName(), LoggerFactory.getLogger(object.getClass()));
        }
        return classLoggerMap.get(object.getClass().getName());
    }
}
