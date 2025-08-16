package com.tramas.infrastructure.adapter.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        String error,
        String message,
        int status,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp,
        String path,
        List<String> details
) {
    
    public ErrorResponse(String error, String message, int status, String path) {
        this(error, message, status, LocalDateTime.now(), path, null);
    }
    
    public ErrorResponse(String error, String message, int status, String path, List<String> details) {
        this(error, message, status, LocalDateTime.now(), path, details);
    }
}