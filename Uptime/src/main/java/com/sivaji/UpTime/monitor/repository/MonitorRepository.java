package com.sivaji.UpTime.monitor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sivaji.UpTime.monitor.entity.Monitor;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {

    // Get all monitors of a user
    List<Monitor> findByUserId(Long userId);

    // Get only active monitors (used later for scheduler)
    List<Monitor> findByActiveTrue();

    // Find monitor by id and user (security check)
    Optional<Monitor> findByIdAndUserId(Long monitorId, Long userId);

}