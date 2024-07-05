package com.acuicola2h.monitor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LogContext {
	
	public LogContext(String messageId, String methodName, String message) {
		super();
		this.messageId = messageId;
		this.methodName = methodName;
		this.message = message;
	}
	private String messageId;
	private String methodName;
	private String message;

}
