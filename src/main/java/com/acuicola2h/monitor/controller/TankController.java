package com.acuicola2h.monitor.controller;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.entity.FishTank;
import com.acuicola2h.monitor.entity.TankMeasurement;
import com.acuicola2h.monitor.model.LogContext;
import com.acuicola2h.monitor.service.FishTankService;
import com.acuicola2h.monitor.service.TankMeasurementService;
import com.acuicola2h.monitor.util.LoggerUtility;

@RestController

@RequestMapping("/tank")
public class TankController {
	
	@Autowired
	private FishTankService fishTankService;

    @Autowired
    private TankMeasurementService tankMeasurementService;
    
    Logger log = LoggerFactory.getLogger(TankController.class);
    
    @GetMapping("/getAllTanks")
    public ResponseEntity<List<FishTank>> getAllTanks() {
    	LogContext logContext = new LogContext("Message Id", "getAllTanks", "");
    	LoggerUtility.printMethodStart(getClass(), Level.INFO, logContext);
    	ResponseEntity<List<FishTank>> responseEntity = null;
    	try {
    		List<FishTank> response = fishTankService.getAllFishTanks();
    		responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
    	} catch (Exception e) {
    		LoggerUtility.printExceptionLog(getClass(), Level.ERROR, logContext, e);
    		responseEntity = ResponseEntity.status(500).build();
    	}
    	return responseEntity;
    }

    @GetMapping("/batch/{batchId}")
    public List<TankMeasurement> getMeasurementsByBatch(@PathVariable Long batchId) {
        return tankMeasurementService.getMeasurementsByBatch(batchId);
    }

    @GetMapping("/fishtank/{fishTankId}")
    public List<TankMeasurement> getMeasurementsByFishTank(@PathVariable Long fishTankId) {
        return tankMeasurementService.getMeasurementsByFishTank(fishTankId);
    }
    
//    @PostMapping(value = "/saveNotes", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<FishTank> saveNotes(@RequestBody FishTank fishTank) {
//    	LogContext logContext = new LogContext("Message Id", "saveNotes", "Test message");
//    	LoggerUtility.printMethodStart(getClass(), Level.INFO, logContext);
//    	ResponseEntity<FishTank> responseEntity = null;
//    	try {
//    		FishTank serviceResponse = fishTankService.saveNotes(fishTank);
//    		responseEntity = new ResponseEntity<>(serviceResponse, HttpStatus.OK);
//    	} catch (Exception e) {
//    		LoggerUtility.printExceptionLog(getClass(), Level.ERROR, logContext, e);
//    		responseEntity = ResponseEntity.status(500).build();
//    	}
//    	LoggerUtility.printMethodEnd(getClass(), Level.INFO, logContext);
//    	return responseEntity;
//    }

    @PostMapping("/create")
    public TankMeasurement createMeasurement(@RequestBody TankMeasurement measurement) {
        return tankMeasurementService.saveMeasurement(measurement);
    }
    
    @GetMapping("/createTestTank")
    public ResponseEntity<List<TankMeasurement>> createTestTank() {
    	try {
    		
            List<TankMeasurement> dummyData = tankMeasurementService.createTestTank();
            return ResponseEntity.status(201).body(dummyData);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
