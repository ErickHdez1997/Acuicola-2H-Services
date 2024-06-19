package com.acuicola2h.monitor.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.dto.LoginRequestDto;
import com.acuicola2h.monitor.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private AuthService authService;
	
    Logger log = LoggerFactory.getLogger(AuthController.class);
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody LoginRequestDto loginRequest) {
    	log.info(loginRequest.getUsername());
    	log.info(loginRequest.getPassword());
    	String token = authService.authenticate(loginRequest);
    	log.info(token);
        if (token != null) {
        	Map<String, String> response = new HashMap<>();
	        response.put("token", token);
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(401).body(null);
	    }
    }
}
