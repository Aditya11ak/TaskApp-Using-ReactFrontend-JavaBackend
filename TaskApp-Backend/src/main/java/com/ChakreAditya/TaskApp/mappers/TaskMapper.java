package com.ChakreAditya.TaskApp.mappers;

import com.ChakreAditya.TaskApp.domain.dto.TaskDTO;
import com.ChakreAditya.TaskApp.tables.Task;

public interface TaskMapper {
    Task fromDTO(TaskDTO taskDTO);
    TaskDTO toDTO(Task task);
}
