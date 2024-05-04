package com.acuicola2h.monitor.service;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	public Future<Boolean> sendEmail(List<String> list);
	
}
