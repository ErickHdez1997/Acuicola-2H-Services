package com.acuicola2h.monitor.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.dto.LoginRequestDto;
import com.acuicola2h.monitor.entity.UserEntity;
import com.acuicola2h.monitor.entity.UserPassword;
import com.acuicola2h.monitor.repository.UserPasswordRepository;
import com.acuicola2h.monitor.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPasswordRepository userPasswordRepository;
    
    private static final String SECRET_KEY = "UjehzQAVPSBl50DbkA3TkXVMYHhKDw7vfrSU2OXb7U0="; // Use a strong secret key
    
    private static final SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String authenticate(LoginRequestDto loginRequest) {
        UserEntity userEntity = userRepository.findByUsername(loginRequest.getUsername())
                .orElse(null);

        if (userEntity != null) {
            UserPassword password = userPasswordRepository.findTopByUserOrderByDateChangedDesc(userEntity)
                    .orElse(null);

            if (password != null && passwordEncoder.matches(loginRequest.getPassword(), password.getEncryptedPassword())) {
                // Generate a token (e.g., JWT) and return it
                return generateToken(userEntity);
            }
        }
        return null;
    }
    
    private String generateToken(UserEntity userEntity) {
        long expirationTime = 1000 * 60 * 60; // 1 hour
        
        return Jwts.builder()
        		.subject(userEntity.getUsername())
        		.issuedAt(new Date())
        		.expiration(new Date(System.currentTimeMillis() + expirationTime))
        		//.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
        		.signWith(key)
        		.compact();
    }
	
}
