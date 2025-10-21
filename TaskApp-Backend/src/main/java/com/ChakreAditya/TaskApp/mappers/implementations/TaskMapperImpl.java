package com.ChakreAditya.TaskApp.mappers.implementations;

import com.ChakreAditya.TaskApp.domain.dto.TaskDTO;
import com.ChakreAditya.TaskApp.mappers.TaskMapper;
import com.ChakreAditya.TaskApp.tables.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDTO(TaskDTO taskDTO) {
        return new Task(
                taskDTO.id(),
                taskDTO.title(),
                taskDTO.description(),
                taskDTO.dueDate(),
                taskDTO.status(),
                taskDTO.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus(),
                task.getPriority()
        );
    }
}
