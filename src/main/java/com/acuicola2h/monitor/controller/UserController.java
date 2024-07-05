package com.acuicola2h.monitor.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.dto.LoginRequestDto;
import com.acuicola2h.monitor.dto.LoginResponseDto;
import com.acuicola2h.monitor.dto.RegistrationDto;
import com.acuicola2h.monitor.dto.UserProfileInfoDto;
import com.acuicola2h.monitor.entity.Batch;
import com.acuicola2h.monitor.model.LogContext;
import com.acuicola2h.monitor.service.AuthService;
import com.acuicola2h.monitor.service.RegistrationService;
import com.acuicola2h.monitor.service.UserService;
import com.acuicola2h.monitor.util.Constants;
import com.acuicola2h.monitor.util.LoggerUtility;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private AuthService authService;
    private RegistrationService registrationService;
	private UserService userService;
    
	public UserController(AuthService authService, RegistrationService registrationService, UserService userService) {
		this.authService = authService;
		this.registrationService = registrationService;
		this.userService = userService;
	}
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody LoginRequestDto loginRequest) {
    	LogContext logContext = new LogContext("Message Id", "authenticateUser", "Test message");
    	LoggerUtility.printMethodStart(getClass(), Level.INFO, logContext);
    	ResponseEntity<LoginResponseDto> responseEntity = null;
    	LoginResponseDto loginResponseDto = null;
    	try {
    		loginResponseDto = authService.authenticate(loginRequest);
            if (loginResponseDto != null) {
            	loginResponseDto.setResponseStatus(Constants.RESPONSE_STATUS_SUCCESS);
            	responseEntity = new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    	    } else {
    	    	loginResponseDto = new LoginResponseDto();
            	loginResponseDto.setResponseStatus(Constants.RESPONSE_STATUS_FAILURE);
            	loginResponseDto.setResponseMessage(Constants.RESPONSE_STATUS_UNAUTHORIZED);
    	    	responseEntity = new ResponseEntity<>(loginResponseDto, HttpStatus.UNAUTHORIZED);
    	    }
    	} catch (Exception e) {
    		LoggerUtility.printExceptionLog(getClass(), Level.ERROR, logContext, e);
    		loginResponseDto = new LoginResponseDto();
    		loginResponseDto.setResponseStatus(Constants.RESPONSE_STATUS_FAILURE);
    		loginResponseDto.setResponseMessage(e.getMessage());
    		responseEntity = new ResponseEntity<>(loginResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	LoggerUtility.printMethodEnd(getClass(), Level.INFO, logContext);
    	return responseEntity;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegistrationDto userRegistrationDto) {
    	registrationService.registerUser(userRegistrationDto);
    	Map<String, String> response = new HashMap<>();
    	response.put("response", "Success");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/fetchUserProfile")
    public ResponseEntity<UserProfileInfoDto> fetchUserProfileById(@RequestBody long userId) {
    	LogContext logContext = new LogContext("TesT", "fetchUserProfileById", "Test message");
    	LoggerUtility.printMethodStart(getClass(), Level.INFO, logContext);
    	ResponseEntity<UserProfileInfoDto> responseEntity = null;
    	try {
    		UserProfileInfoDto serviceResponse = userService.fetchUserProfileById(userId);
    		responseEntity = new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		LoggerUtility.printExceptionLog(getClass(), Level.ERROR, logContext, e);
    		responseEntity = ResponseEntity.status(500).build();
    	}
    	LoggerUtility.printMethodEnd(getClass(), Level.INFO, logContext);
    	return responseEntity;
    }
    
    
    //To do change this into a update user profile info service
    @PostMapping(value = "/saveUserNotes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileInfoDto> saveUserNotes(@RequestBody UserProfileInfoDto userProfile) {
    	LogContext logContext = new LogContext("Message Id", "saveUserNotes", "Test message");
    	LoggerUtility.printMethodStart(getClass(), Level.INFO, logContext);
    	ResponseEntity<UserProfileInfoDto> responseEntity = null;
    	try {
    		UserProfileInfoDto serviceResponse = userService.saveNotes(userProfile);
    		responseEntity = new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		LoggerUtility.printExceptionLog(getClass(), Level.ERROR, logContext, e);
    		responseEntity = ResponseEntity.status(500).build();
    	}
    	LoggerUtility.printMethodEnd(getClass(), Level.INFO, logContext);
    	return responseEntity;
    }
}
