package com.acuicola2h.monitor.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acuicola2h.monitor.dto.CreateBatchRequestDto;
import com.acuicola2h.monitor.entity.Batch;
import com.acuicola2h.monitor.model.LogContext;
import com.acuicola2h.monitor.service.BatchService;
import com.acuicola2h.monitor.util.LoggerUtility;

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
    
    @PostMapping(value = "/saveBatchNotes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> saveBatchNotes(@RequestBody Batch batch) {
    	LogContext logContext = new LogContext("Message Id", "saveBatchNotes", "Test message");
    	LoggerUtility.printMethodStart(getClass(), Level.INFO, logContext);
    	ResponseEntity<Batch> responseEntity = null;
    	try {
    		Batch serviceResponse = batchService.saveNotes(batch);
    		responseEntity = new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    	} catch (Exception e) {
    		LoggerUtility.printExceptionLog(getClass(), Level.ERROR, logContext, e);
    		responseEntity = ResponseEntity.status(500).build();
    	}
    	LoggerUtility.printMethodEnd(getClass(), Level.INFO, logContext);
    	return responseEntity;
    }

}
