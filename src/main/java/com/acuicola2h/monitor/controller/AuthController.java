package com.acuicola2h.monitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.dto.LoginRequestDto;
import com.acuicola2h.monitor.dto.LoginResponseDto;
import com.acuicola2h.monitor.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private AuthService authService;
	
    Logger log = LoggerFactory.getLogger(AuthController.class);
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody LoginRequestDto loginRequest) {
    	String token = authService.authenticate(loginRequest);
    	log.info(token);
        if (token != null) {
        	LoginResponseDto response = new LoginResponseDto();
	        response.setToken(token);
	        log.info(response.toString());
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(401).body(null);
	    }
    }
}
