package com.acuicola2h.monitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acuicola2h.monitor.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Long> {
	List<Batch> findByInProgress(boolean inProgress);
}
