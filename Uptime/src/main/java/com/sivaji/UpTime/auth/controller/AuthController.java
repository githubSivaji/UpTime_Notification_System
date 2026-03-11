package com.sivaji.UpTime.auth.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sivaji.UpTime.auth.dto.RegisterDto;
import com.sivaji.UpTime.auth.service.AuthService;
import com.sivaji.UpTime.common.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor 
public class AuthController {
	
	private AuthService authService;
	@GetMapping  
	public String getMessage()
	{
		return "hello from auth controller";
	}
     
	  @PostMapping("/register")
	    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody RegisterDto request) {

	        authService.register(request);

	        ApiResponse<?> response = new ApiResponse<>(
	                true,
	                "User registered successfully",
	                null,
	                LocalDateTime.now()
	        );

	        return ResponseEntity.ok(response);
	        
	  }
}
