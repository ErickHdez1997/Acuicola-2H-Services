package com.acuicola2h.monitor.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.dto.RegistrationDto;
import com.acuicola2h.monitor.service.RegistrationService;

@RestController
@RequestMapping("/api/users")
public class RegistrationController {

	@Autowired
    private RegistrationService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegistrationDto userRegistrationDto) {
    	userService.registerUser(userRegistrationDto);
    	Map<String, String> response = new HashMap<>();
    	response.put("response", "Success");
        return ResponseEntity.ok(response);
    }
	
}
