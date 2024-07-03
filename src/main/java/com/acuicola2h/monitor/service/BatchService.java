package com.acuicola2h.monitor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.dto.CreateBatchRequestDto;
import com.acuicola2h.monitor.entity.Batch;
import com.acuicola2h.monitor.entity.FishTank;
import com.acuicola2h.monitor.repository.BatchRepository;
import com.acuicola2h.monitor.repository.FishTankRepository;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;
    
    @Autowired
    private FishTankRepository fishTankRepository;

    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }
    
    public List<Batch> getActiveBatches() {
    	return batchRepository.findByInProgress(true);
    }
    
    public Batch createBatch(CreateBatchRequestDto request) {
    	
    	//Get Fish Tank
    	FishTank fishTank = fishTankRepository.getReferenceById(request.getFishTankId());
    	
    	//To do - add logic to check if that tank is already assigned
    	
    	Batch batch = new Batch();
    	batch.setFishTank(fishTank);
    	//batch.setStartDate(LocalDate.now());
    	batch.setStartDate(request.getStartDate());
    	return batchRepository.save(batch);
    }
    
    public Optional<Batch> getBatchById(Long batchId) {
    	return batchRepository.findById(batchId);
    }

    public Batch saveBatch(Batch batch) {
        return batchRepository.save(batch);
    }

}
