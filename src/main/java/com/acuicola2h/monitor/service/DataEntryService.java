package com.acuicola2h.monitor.service;

import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.model.DataEntry;

@Service
public interface DataEntryService {
	
	public String processDataEntry(DataEntry dataEntry);

}
