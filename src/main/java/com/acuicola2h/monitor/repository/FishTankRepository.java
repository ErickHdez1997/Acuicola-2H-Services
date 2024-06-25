package com.acuicola2h.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acuicola2h.monitor.entity.FishTank;

public interface FishTankRepository extends JpaRepository<FishTank, Long> {
}