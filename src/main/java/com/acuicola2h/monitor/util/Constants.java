package com.acuicola2h.monitor.util;

public class Constants {
	
	private Constants() {
		super();
	}
	
	//Controller Paths
	public static final String CONTROLLER_PATH = "/monitor";
	public static final String PROCESS_FILE_PATH = "/processFile";
	public static final String PROCESS_DATA_ENTRY_PATH = "/processDataEntry";
	
	//Responses
	public static final String RESPONSE_STATUS_SUCCESS = "SUCCESS";
	public static final String RESPONSE_STATUS_FAILURE = "FAILURE";
	public static final String RESPONSE_STATUS_UNAUTHORIZED = "Username or password is incorrect. Please try again.";
	
	//Logger
	public static final String LOG_START = "Log start";
	public static final String LOG_END = "Log end";
	
}
