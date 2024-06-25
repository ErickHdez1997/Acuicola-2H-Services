package com.acuicola2h.monitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acuicola2h.monitor.entity.TankMeasurement;

public interface TankMeasurementRepository extends JpaRepository<TankMeasurement, Long> {
    List<TankMeasurement> findByBatchId(Long batchId);
    List<TankMeasurement> findByFishTankId(Long fishTankId);
}
