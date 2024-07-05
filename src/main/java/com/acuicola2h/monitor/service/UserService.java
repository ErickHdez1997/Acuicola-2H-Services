package com.acuicola2h.monitor.service;

import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.dto.UserProfileInfoDto;
import com.acuicola2h.monitor.entity.UserEntity;
import com.acuicola2h.monitor.model.LogContext;
import com.acuicola2h.monitor.repository.UserRepository;
import com.acuicola2h.monitor.util.LoggerUtility;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserProfileInfoDto fetchUserProfileById(long id) {
		LogContext logContext = new LogContext("", "fetchUserProfileById", "");
		LoggerUtility.printMethodStart(getClass(), Level.WARN, logContext);
		UserProfileInfoDto userProfile = new UserProfileInfoDto();
		try {
			UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
			userProfile.setId(user.getId());
			userProfile.setFirstName(user.getFirstName());
			userProfile.setLastName(user.getLastName());
			userProfile.setEmail(user.getEmail());
			userProfile.setNotes(user.getNotes());
			userProfile.setAdmin(user.isAdmin());
			userProfile.setCreatedDate(user.getCreatedDate());
			userProfile.setCreatedBy(user.getCreatedBy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfile;
	}
	
	public UserProfileInfoDto saveNotes(UserProfileInfoDto updatedUserProfile) {
		UserEntity user = userRepository.findById(updatedUserProfile.getId()).orElseThrow(() -> new RuntimeException("User not found"));
		user.setNotes(updatedUserProfile.getNotes());
		user = userRepository.save(user);
		UserProfileInfoDto userProfile = new UserProfileInfoDto();
		userProfile.setId(user.getId());
		userProfile.setFirstName(user.getFirstName());
		userProfile.setLastName(user.getLastName());
		userProfile.setEmail(user.getEmail());
		userProfile.setNotes(user.getNotes());
		userProfile.setAdmin(user.isAdmin());
		userProfile.setCreatedDate(user.getCreatedDate());
		userProfile.setCreatedBy(user.getCreatedBy());
		return userProfile;
	}

}
