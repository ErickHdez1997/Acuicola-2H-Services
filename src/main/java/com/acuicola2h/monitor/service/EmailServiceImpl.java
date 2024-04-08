package com.acuicola2h.monitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	public String sendEmail(List<String> list) {
		return "done";
	}
	
}
