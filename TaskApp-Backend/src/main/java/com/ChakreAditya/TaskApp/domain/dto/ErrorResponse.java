package com.ChakreAditya.TaskApp.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
