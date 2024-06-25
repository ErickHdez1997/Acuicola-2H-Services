package com.acuicola2h.monitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.entity.Batch;
import com.acuicola2h.monitor.entity.FishTank;
import com.acuicola2h.monitor.entity.TankMeasurement;
import com.acuicola2h.monitor.repository.TankMeasurementRepository;

@Service
public class TankMeasurementService {

    @Autowired
    private TankMeasurementRepository tankMeasurementRepository;
    
    @Autowired
    private FishTankService fishTankService;
    
    @Autowired
    private BatchService batchService;

    public List<TankMeasurement> getMeasurementsByBatch(Long batchId) {
        return tankMeasurementRepository.findByBatchId(batchId);
    }

    public List<TankMeasurement> getMeasurementsByFishTank(Long fishTankId) {
        return tankMeasurementRepository.findByFishTankId(fishTankId);
    }

    public TankMeasurement saveMeasurement(TankMeasurement measurement) {
        return tankMeasurementRepository.save(measurement);
    }
    
    public List<TankMeasurement> createTestTank() {
    	// Create a dummy fish tank
        FishTank fishTank = new FishTank();
        fishTank.setName("Dummy Tank");
        fishTank = fishTankService.saveFishTank(fishTank);

        // Create a dummy batch
        Batch batch = new Batch();
        batch.setFishTank(fishTank);
        batch.setStartDate(new Date());
        batch.setInProgress(true);
        batch.setFishPlanted(100);
        batch = batchService.saveBatch(batch);

        // Create multiple dummy measurements
        List<TankMeasurement> measurements = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TankMeasurement measurement = new TankMeasurement();
            measurement.setBatch(batch);
            measurement.setFishTank(fishTank);
            measurement.setOxygen(5.5 + i);
            measurement.setPH(7.0);
            measurement.setTemperature(25.0 + i);
            measurement.setSalinity(30.0);
            measurement.setNitrate(1.8);
            measurement.setNitrite(0.05);
            measurement.setAmmonia(0.03);
            measurement.setTurbine(10.0);
            measurement.setAlkalinity(100.0);
            measurement.setDeaths(0);
            measurement.setDate(new Date());
            measurements.add(tankMeasurementRepository.save(measurement));
        }

        return measurements;
    }

}
