package com.acuicola2h.monitor.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.dto.UserRegistrationDto;
import com.acuicola2h.monitor.entity.User;
import com.acuicola2h.monitor.entity.UserPassword;
import com.acuicola2h.monitor.repository.UserPasswordRepository;
import com.acuicola2h.monitor.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private UserPasswordRepository userPasswordRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Implement a registerUserResponseObject
    public String registerUser(UserRegistrationDto userRegistrationDto) {
    	User user = new User();
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
        userPassword.setDateChanged(LocalDateTime.now());

        userPasswordRepository.save(userPassword);
        
        return "OK";
        
    }
	
}
