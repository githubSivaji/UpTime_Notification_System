package com.sivaji.UpTime.monitor.entity;

import java.time.LocalDateTime;

import com.sivaji.UpTime.auth.entity.User;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "monitors")
@Getter
@Setter
public class Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    // HTTP / TCP / PING
    @Column(nullable = false)
    private String type;

    // HTTP method (GET, POST, HEAD)
    private String method;

    @Column(nullable = false)
    private Integer intervalSeconds;

    @Column(nullable = false)
    private Integer timeoutSeconds;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column
    private Integer expectedStatusCode;

    @Column(columnDefinition = "TEXT")
    private String headers;
}