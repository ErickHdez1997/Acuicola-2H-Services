package com.acuicola2h.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acuicola2h.monitor.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
