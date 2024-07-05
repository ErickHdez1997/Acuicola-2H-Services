package com.acuicola2h.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.entity.FishTank;
import com.acuicola2h.monitor.repository.FishTankRepository;

@Service
public class FishTankService {

    @Autowired
    private FishTankRepository fishTankRepository;

    public List<FishTank> getAllFishTanks() {
        List<FishTank> fishTanks = fishTankRepository.findAll();
        fishTanks.forEach(fishTank -> fishTank.setActiveBatchId(fishTank.getActiveBatchId()));
        return fishTanks;
    }
    
    public FishTank getTankById(long id) {
    	FishTank tank = fishTankRepository.getReferenceById(id);
    	return tank;
    }

    public FishTank saveFishTank(FishTank fishTank) {
        return fishTankRepository.save(fishTank);
    }

}