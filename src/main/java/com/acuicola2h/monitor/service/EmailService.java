package com.acuicola2h.monitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	public String sendEmail(List<String> list);
	
}
