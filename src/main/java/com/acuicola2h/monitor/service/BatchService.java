package com.acuicola2h.monitor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.entity.Batch;
import com.acuicola2h.monitor.repository.BatchRepository;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }
    
    public List<Batch> getActiveBatches() {
    	return batchRepository.findByInProgress(true);
    }
    
    public Optional<Batch> getBatchById(Long batchId) {
    	return batchRepository.findById(batchId);
    }

    public Batch saveBatch(Batch batch) {
        return batchRepository.save(batch);
    }

}
