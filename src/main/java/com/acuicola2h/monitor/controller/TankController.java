package com.acuicola2h.monitor.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.entity.FishTank;
import com.acuicola2h.monitor.entity.TankMeasurement;
import com.acuicola2h.monitor.service.FishTankService;
import com.acuicola2h.monitor.service.TankMeasurementService;

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
    	try {
    		List<FishTank> response = fishTankService.getAllFishTanks();
    		return ResponseEntity.ok(response);
    	} catch (Exception e) {
    		return ResponseEntity.status(500).build();
    	}
    }

    @GetMapping("/batch/{batchId}")
    public List<TankMeasurement> getMeasurementsByBatch(@PathVariable Long batchId) {
        return tankMeasurementService.getMeasurementsByBatch(batchId);
    }

    @GetMapping("/fishtank/{fishTankId}")
    public List<TankMeasurement> getMeasurementsByFishTank(@PathVariable Long fishTankId) {
        return tankMeasurementService.getMeasurementsByFishTank(fishTankId);
    }
    
    @PostMapping(value = "/saveNotes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FishTank> saveNotes(@RequestBody FishTank fishTank) {
    	log.info("Fish Tank {}", fishTank);
    	try {
    		FishTank response = fishTankService.saveNotes(fishTank);
        	return ResponseEntity.ok(response);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseEntity.status(500).build();
    	}
    	 
    }

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
