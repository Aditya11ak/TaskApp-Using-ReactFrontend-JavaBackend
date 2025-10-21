package com.ChakreAditya.TaskApp.mappers.implementations;

import com.ChakreAditya.TaskApp.domain.dto.TaskListDTO;
import com.ChakreAditya.TaskApp.domain.entities.TaskStatus;
import com.ChakreAditya.TaskApp.mappers.TaskListMapper;
import com.ChakreAditya.TaskApp.mappers.TaskMapper;
import com.ChakreAditya.TaskApp.tables.Task;
import com.ChakreAditya.TaskApp.tables.TaskList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    @Autowired
    TaskMapper taskMapper;


    @Override
    public TaskList fromDTO(TaskListDTO taskListDTO) {

        return new TaskList(
                taskListDTO.id(),
                taskListDTO.title(),
                taskListDTO.description(),
                null,
                null,
                Optional.ofNullable(taskListDTO.tasks())
                        .map(tasks
                                ->tasks
                                    .stream()
                                    .map(taskMapper::fromDTO)// here:  taskDTO -> taskMapper.fromDTO(taskDTO)
                                    .toList()
                        ).orElse(null)
        );
    }

    @Override
    public TaskListDTO toDTO(TaskList taskList) {
        return new TaskListDTO(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks()).
                        map(List::size)
                        .orElse(0),calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks ->
                                tasks.stream()
                                        .map(taskMapper::toDTO)
                                        .toList()
                        ).orElse(null)
        );

    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if(tasks == null){
            return null;
        }

        long completedTaskCount = tasks.stream()
                .filter(task->
                        TaskStatus.COMPLETED == task.getStatus() )
                .count();

        return (double) completedTaskCount/tasks.size();
    }

}
