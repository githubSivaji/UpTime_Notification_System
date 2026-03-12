package com.sivaji.UpTime.monitor.service;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sivaji.UpTime.auth.entity.User;
import com.sivaji.UpTime.auth.repository.UserRepository;
import com.sivaji.UpTime.common.exception.ResourceNotFoundException;
import com.sivaji.UpTime.monitor.dto.CreateMonitorDto;
import com.sivaji.UpTime.monitor.entity.Monitor;
import com.sivaji.UpTime.monitor.repository.MonitorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonitorService {

    private final MonitorRepository monitorRepository;
    private final UserRepository userRepository;
    

    public Monitor createMonitor(CreateMonitorDto request) {

        Long userId = Long.parseLong(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Monitor monitor = new Monitor();

        monitor.setName(request.getName());
        monitor.setUrl(request.getUrl());
        monitor.setType(request.getType());

        monitor.setMethod(request.getMethod());
        monitor.setExpectedStatusCode(request.getExpectedStatusCode());
        monitor.setHeaders(request.getHeaders());

        monitor.setIntervalSeconds(request.getIntervalSeconds());
        monitor.setTimeoutSeconds(request.getTimeoutSeconds());

        monitor.setCreatedAt(LocalDateTime.now());
        monitor.setUser(user);

        return monitorRepository.save(monitor);
    }

}