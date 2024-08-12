package com.acuicola2h.monitor.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.dto.NewTankMeasurementRequest;
import com.acuicola2h.monitor.entity.Batch;
import com.acuicola2h.monitor.entity.FishTank;
import com.acuicola2h.monitor.entity.TankMeasurement;
import com.acuicola2h.monitor.entity.TankMeasurementId;
import com.acuicola2h.monitor.repository.BatchRepository;
import com.acuicola2h.monitor.repository.FishTankRepository;
import com.acuicola2h.monitor.repository.TankMeasurementRepository;

import jakarta.transaction.Transactional;

@Service
public class TankMeasurementService {

    @Autowired
    private TankMeasurementRepository tankMeasurementRepository;
    
    @Autowired
    private FishTankService fishTankService;
    
    @Autowired
    private BatchService batchService;
    
    @Autowired
    private BatchRepository	batchRepository;
    
    @Autowired
    private FishTankRepository fishTankRepository;

    public List<TankMeasurement> getMeasurementsByBatch(Long batchId) {
        return tankMeasurementRepository.findAllByBatchId(batchId);
    }

    public List<TankMeasurement> getMeasurementsByFishTank(Long fishTankId) {
        return tankMeasurementRepository.findByFishTankId(fishTankId);
    }

    public TankMeasurement saveMeasurement(NewTankMeasurementRequest measurement) {
    	FishTank tank = fishTankRepository.findById(measurement.getFishTankId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid tank ID"));
        Batch batch = batchRepository.findById(measurement.getBatchId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid batch ID"));
        Long nextMeasurementId = tankMeasurementRepository
                .findMaxMeasurementIdByBatchId(measurement.getBatchId())
                .orElse(0L) + 1;
        TankMeasurement tankMeasurement = new TankMeasurement();
        tankMeasurement.setFishTank(tank);
        tankMeasurement.setAlkalinity(measurement.getAlkalinity());
        tankMeasurement.setAmmonia(measurement.getAmmonia());
        tankMeasurement.setDate(measurement.getDate());
        tankMeasurement.setDeaths(measurement.getDeaths());
        tankMeasurement.setNitrate(measurement.getNitrate());
        tankMeasurement.setNitrite(measurement.getNitrite());
        tankMeasurement.setOxygen(measurement.getOxygen());
        tankMeasurement.setPH(measurement.getPH());
        tankMeasurement.setSalinity(measurement.getSalinity());
        
        TankMeasurementId measurementId = new TankMeasurementId(measurement.getBatchId(), nextMeasurementId);
        tankMeasurement.setId(measurementId);
        tankMeasurement.setBatch(batch);
        return tankMeasurementRepository.save(tankMeasurement);
    }
    
    public List<TankMeasurement> deleteMeasurements(List<TankMeasurement> measurementsToDelete) {
    	tankMeasurementRepository.deleteAllInBatch(measurementsToDelete);
    	long batchId = measurementsToDelete.get(0).getId().getBatchId();
    	return tankMeasurementRepository.findAllByBatchId(batchId);
    }
    
    public List<TankMeasurement> createTestTank() {
    	
    	List<FishTank> fishTanks = fishTankService.getAllFishTanks();
        
        if (fishTanks.size() == 0) {
        	for (int i = 0; i < 24; i++) {
        		// Create a dummy fish tank
                FishTank fishTank = new FishTank();
                fishTank.setName("Dummy Tank "+(i+1));
                fishTank = fishTankService.saveFishTank(fishTank);
        	}
        	
        } else {
        	return null;
        }
       
        List<TankMeasurement> measurements = new ArrayList<>();
        
        for (int j = 0; j < 24; j++) {
        	if (j % 3 == 0) {
        		// Create a dummy batch
                Batch batch = new Batch();
                
                FishTank tank = fishTankService.getTankById(j+1);
                
                batch.setFishTank(tank);
                batch.setStartDate(LocalDate.now());
                batch.setInProgress(true);
                batch.setFishPlanted(100);
                batch = batchService.saveBatch(batch);
                
                DecimalFormat df = new DecimalFormat("#.###");
                
                // Create multiple dummy measurements
                for (int i = 0; i < (int)Math.round(Math.random()*50) ; i++) {
                    TankMeasurement measurement = new TankMeasurement();
                    measurement.setBatch(batch);
                    measurement.setFishTank(tank);
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
//                    measurements.add(tankMeasurementRepository.save(measurement));
                    measurements.add(createMeasurement(batch.getId(), measurement));
                }
        	}
        }

        return measurements;
    }
    
    @Transactional
    public TankMeasurement createMeasurement(Long batchId, TankMeasurement measurement) {
        Batch batch = batchRepository.findById(batchId).orElseThrow(() -> new IllegalArgumentException("Invalid batch ID"));
        Long nextMeasurementId = tankMeasurementRepository.findMaxMeasurementIdByBatchId(batchId).orElse(0L) + 1;

        TankMeasurementId measurementId = new TankMeasurementId(batchId, nextMeasurementId);
        measurement.setId(measurementId);
        measurement.setBatch(batch);

        return tankMeasurementRepository.save(measurement);
    }

}
