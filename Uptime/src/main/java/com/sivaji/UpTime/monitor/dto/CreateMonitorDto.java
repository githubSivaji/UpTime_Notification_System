package com.sivaji.UpTime.monitor.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateMonitorDto {

    @NotBlank(message = "Monitor name is required")
    private String name;

    @NotBlank(message = "URL is required")
    private String url;

    @NotBlank(message = "Monitor type is required")
    private String type; // HTTP, TCP, PING


    // Optional fields
    private String method; // GET, POST, HEAD

    private Integer expectedStatusCode;

    private String headers;


    @NotNull(message = "Interval is required")
    @Min(value = 10, message = "Interval must be at least 10 seconds")
    private Integer intervalSeconds;


    @NotNull(message = "Timeout is required")
    @Min(value = 1, message = "Timeout must be at least 1 second")
    private Integer timeoutSeconds;

}