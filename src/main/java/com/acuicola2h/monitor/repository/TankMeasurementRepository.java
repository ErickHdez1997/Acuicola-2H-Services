package com.acuicola2h.monitor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acuicola2h.monitor.entity.TankMeasurement;
import com.acuicola2h.monitor.entity.TankMeasurementId;

public interface TankMeasurementRepository extends JpaRepository<TankMeasurement, TankMeasurementId> {
    List<TankMeasurement> findByBatchId(Long batchId);
    List<TankMeasurement> findByFishTankId(Long fishTankId);
    @Query("SELECT MAX(tm.id.measurementId) FROM TankMeasurement tm WHERE tm.id.batchId = :batchId")
    Optional<Long> findMaxMeasurementIdByBatchId(Long batchId);
}
