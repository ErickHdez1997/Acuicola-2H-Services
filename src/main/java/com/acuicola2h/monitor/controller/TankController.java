package com.acuicola2h.monitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.entity.TankMeasurement;
import com.acuicola2h.monitor.service.TankMeasurementService;

@RestController
@RequestMapping("/tank")
public class TankController {

    @Autowired
    private TankMeasurementService tankService;

    @GetMapping("/batch/{batchId}")
    public List<TankMeasurement> getMeasurementsByBatch(@PathVariable Long batchId) {
        return tankService.getMeasurementsByBatch(batchId);
    }

    @GetMapping("/fishtank/{fishTankId}")
    public List<TankMeasurement> getMeasurementsByFishTank(@PathVariable Long fishTankId) {
        return tankService.getMeasurementsByFishTank(fishTankId);
    }

    @PostMapping("/create")
    public TankMeasurement createMeasurement(@RequestBody TankMeasurement measurement) {
        return tankService.saveMeasurement(measurement);
    }
    
    @GetMapping("/createTestTank")
    public ResponseEntity<List<TankMeasurement>> createTestTank() {
    	try {
            List<TankMeasurement> dummyData = tankService.createTestTank();
            return ResponseEntity.status(201).body(dummyData);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
