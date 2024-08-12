package com.acuicola2h.monitor.controller;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.dto.NewTankMeasurementRequest;
import com.acuicola2h.monitor.entity.TankMeasurement;
import com.acuicola2h.monitor.model.LogContext;
import com.acuicola2h.monitor.service.TankMeasurementService;
import com.acuicola2h.monitor.util.LoggerUtility;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {
	
	@Autowired
	private TankMeasurementService tankMeasurementService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementController.class);
	
	@PostMapping("/addMeasurement")
	public ResponseEntity<TankMeasurement> addMeasurement(@RequestBody NewTankMeasurementRequest measurementToAdd) {
		LogContext logContext = new LogContext("TesT", "addMeasurement", "Test message");
    	LoggerUtility.printMethodStart(getClass(), Level.INFO, logContext);
    	ResponseEntity<TankMeasurement> responseEntity = null;
		try {
			LOGGER.info("Measurement: {}", measurementToAdd.toString());
			TankMeasurement newMeasurement = tankMeasurementService.saveMeasurement(measurementToAdd);
			LOGGER.warn("measurementsToAdd- {}", measurementToAdd);
			LOGGER.warn("Batch Id - {}", newMeasurement.getId().getBatchId());
			LOGGER.warn("newMeasurement - {}", newMeasurement);
			responseEntity = new ResponseEntity<>(newMeasurement, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		LoggerUtility.printExceptionLog(getClass(), Level.ERROR, logContext, e);
    		responseEntity = ResponseEntity.status(500).build();
    	}
    	LoggerUtility.printMethodEnd(getClass(), Level.INFO, logContext);
    	return responseEntity;
	}
	
	@PostMapping("/deleteMeasurements")
	public ResponseEntity<List<TankMeasurement>> deleteMeasurements(@RequestBody List<TankMeasurement> measurementsToDelete) {
		LogContext logContext = new LogContext("TesT", "deleteMeasurements", "Test message");
    	LoggerUtility.printMethodStart(getClass(), Level.INFO, logContext);
    	ResponseEntity<List<TankMeasurement>> responseEntity = null;
		try {
			List<TankMeasurement> tankMeasurements = tankMeasurementService.deleteMeasurements(measurementsToDelete);
			LoggerUtility.printRawLog(getClass(), Level.WARN, logContext, "Size" + Integer.toString(tankMeasurements.size()));
			LOGGER.warn("measurementsToDelete- {}", measurementsToDelete);
			LOGGER.warn("Batch Id - {}", measurementsToDelete.get(0).getId().getBatchId());
			LOGGER.warn("Size - {}", tankMeasurements.size());
			LOGGER.warn("Measurements - {}", tankMeasurements);
			responseEntity = new ResponseEntity<>(tankMeasurements, HttpStatus.OK);
    	} catch (Exception e) {
//    		e.printStackTrace();
    		LoggerUtility.printExceptionLog(getClass(), Level.ERROR, logContext, e);
    		responseEntity = ResponseEntity.status(500).build();
    	}
    	LoggerUtility.printMethodEnd(getClass(), Level.INFO, logContext);
    	return responseEntity;
	}

}
