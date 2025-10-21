package com.ChakreAditya.TaskApp.domain.dto;

import com.ChakreAditya.TaskApp.domain.entities.TaskPriority;
import com.ChakreAditya.TaskApp.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPriority priority
) {
}
