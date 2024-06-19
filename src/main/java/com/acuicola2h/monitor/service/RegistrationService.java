package com.acuicola2h.monitor.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.dto.RegistrationDto;
import com.acuicola2h.monitor.entity.UserEntity;
import com.acuicola2h.monitor.entity.UserPassword;
import com.acuicola2h.monitor.repository.UserRepository;
import com.acuicola2h.monitor.repository.UserPasswordRepository;

@Service
public class RegistrationService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private UserPasswordRepository userPasswordRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Implement a registerUserResponseObject
    public String registerUser(RegistrationDto userRegistrationDto) {
    	UserEntity user = new UserEntity();
    	user.setUsername(userRegistrationDto.getUsername());
    	user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setCreatedDate(LocalDateTime.now());
        user.setCreatedBy("System");
        
        String encryptedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());
        
        userRepository.save(user);
        
        UserPassword userPassword = new UserPassword();
        userPassword.setUser(user);
        userPassword.setEncryptedPassword(encryptedPassword);
        userPassword.setActive(true);
        userPassword.setDateChanged(LocalDateTime.now());

        userPasswordRepository.save(userPassword);
        
        return "OK";
        
    }
	
}
