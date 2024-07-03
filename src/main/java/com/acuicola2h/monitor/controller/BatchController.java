package com.acuicola2h.monitor.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.dto.CreateBatchRequestDto;
import com.acuicola2h.monitor.entity.Batch;
import com.acuicola2h.monitor.service.BatchService;

@RestController
@RequestMapping("/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;

    Logger log = LoggerFactory.getLogger(BatchController.class);
    
    @GetMapping("/active")
    public ResponseEntity<List<Batch>> getActiveBatches() {
        List<Batch> activeBatches = batchService.getActiveBatches();
        log.info("Active Batches: {}", activeBatches);
        return ResponseEntity.ok(activeBatches);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Batch> createBatch(CreateBatchRequestDto request) {
    	Batch createdBatch = batchService.createBatch(request);
    	return ResponseEntity.ok(createdBatch);	
    }
    
    @GetMapping("/{batchId}")
    public ResponseEntity<Batch> getBatchById(@PathVariable Long batchId) {
        Optional<Batch> batch = batchService.getBatchById(batchId);
        //log.info("Batch {}", batch);
        return batch.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
