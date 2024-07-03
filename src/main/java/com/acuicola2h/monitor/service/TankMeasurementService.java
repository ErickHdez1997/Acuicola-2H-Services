package com.acuicola2h.monitor.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
        batch.setStartDate(LocalDate.now());
        batch.setInProgress(true);
        batch.setFishPlanted(100);
        batch = batchService.saveBatch(batch);
        
        DecimalFormat df = new DecimalFormat("#.###");
        
        // Create multiple dummy measurements
        List<TankMeasurement> measurements = new ArrayList<>();
        for (int i = 0; i < (int)Math.round(Math.random()*50) ; i++) {
            TankMeasurement measurement = new TankMeasurement();
            measurement.setBatch(batch);
            measurement.setFishTank(fishTank);
            measurement.setOxygen(Double.parseDouble(df.format(Math.random()*10)));
            measurement.setPH(Double.parseDouble(df.format(Math.random()*12)));
            measurement.setTemperature(Double.parseDouble(df.format(Math.random()*40)));
            measurement.setSalinity(Double.parseDouble(df.format(Math.random()*30)));
            measurement.setNitrate(Double.parseDouble(df.format(Math.random()*2)));
            measurement.setNitrite(Double.parseDouble(df.format(Math.random())));
            measurement.setAmmonia(Double.parseDouble(df.format(Math.random())));
            measurement.setTurbine(Double.parseDouble(df.format(Math.random())));
            measurement.setAlkalinity(Double.parseDouble(df.format(Math.random()*100)));
            measurement.setDeaths((int)Math.round(Math.random()*40));
            LocalDateTime dateTime = LocalDateTime.now();
            LocalDateTime modifiedDateTime = dateTime.plusHours(12*i);
            measurement.setDate(modifiedDateTime);
            measurements.add(tankMeasurementRepository.save(measurement));
        }

        return measurements;
    }

}
