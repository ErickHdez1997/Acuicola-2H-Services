package com.acuicola2h.monitor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.util.Constants;

@RestController(value = Constants.CONTROLLER_PATH)
public class Controller {
	
	@PostMapping(value = Constants.PROCESS_FILE_PATH)
	public ResponseEntity<String> processFile() {
		return new ResponseEntity<>("Done", HttpStatus.OK);
	}
	
}
