package com.ChakreAditya.TaskApp.mappers;

import com.ChakreAditya.TaskApp.domain.dto.TaskListDTO;
import com.ChakreAditya.TaskApp.tables.TaskList;

public interface TaskListMapper {

    TaskList fromDTO(TaskListDTO taskListDTO);
    TaskListDTO toDTO(TaskList taskList);

}
