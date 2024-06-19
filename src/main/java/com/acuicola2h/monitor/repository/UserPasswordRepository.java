package com.acuicola2h.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acuicola2h.monitor.entity.UserPassword;

public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {

}
