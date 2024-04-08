package com.acuicola2h.monitor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.model.DataEntry;
import com.acuicola2h.monitor.service.DataEntryService;
import com.acuicola2h.monitor.util.Constants;

@RestController(value = Constants.CONTROLLER_PATH)
public class Controller {
	
	DataEntryService dataEntryService;
	
	public Controller(DataEntryService dataEntryService) {
		this.dataEntryService = dataEntryService;
	}
	
	@PostMapping(value = Constants.PROCESS_DATA_ENTRY_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> processDataEntry(@RequestBody DataEntry dataEntry) {
		//Validate Request
		//Process
		//Placeholder
		return new ResponseEntity<>(dataEntryService.processDataEntry(dataEntry), HttpStatus.OK);
	}
	
	@PostMapping(value = Constants.PROCESS_FILE_PATH)
	public ResponseEntity<String> processFile() {
		return new ResponseEntity<>("Done", HttpStatus.OK);
	}
	
}
