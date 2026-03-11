package com.sivaji.UpTime.auth.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sivaji.UpTime.auth.dto.RegisterDto;
import com.sivaji.UpTime.auth.entity.User;
import com.sivaji.UpTime.auth.repository.UserRepository;
import com.sivaji.UpTime.common.exception.BadRequestException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class AuthService {
	private UserRepository userRepository;
	private  PasswordEncoder passwordEncoder;
	
	       
	   public void register(RegisterDto request) {

	        if (userRepository.existsByEmail(request.getEmail())) {
	            throw new BadRequestException("Email already registered");
	        }

	        User user = new User();

	        user.setName(request.getName());
	        user.setEmail(request.getEmail());
	        user.setPassword(passwordEncoder.encode(request.getPassword()));
	        user.setCreatedAt(LocalDateTime.now());

	        userRepository.save(user);
	    }

} 
