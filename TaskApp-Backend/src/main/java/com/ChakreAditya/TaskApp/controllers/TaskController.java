package com.ChakreAditya.TaskApp.controllers;

import com.ChakreAditya.TaskApp.domain.dto.TaskDTO;
import com.ChakreAditya.TaskApp.mappers.TaskMapper;
import com.ChakreAditya.TaskApp.services.TaskService;
import com.ChakreAditya.TaskApp.tables.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists/{task_list_id}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDTO> listTasks(@PathVariable("task_list_id")UUID taskListId){
        return taskService.listTasks(taskListId).stream()
                .map(taskMapper::toDTO)
                .toList();

    }

    @PostMapping
    public TaskDTO createTask(@PathVariable("task_list_id")UUID taskListId, @RequestBody TaskDTO taskDTO){

        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDTO(taskDTO));

        return taskMapper.toDTO(createdTask);

    }

    @GetMapping(path = "/{taskId}")
    public Optional<TaskDTO> getTask(
            @PathVariable("task_list_id")UUID taskListId,
            @PathVariable("taskId") UUID taskId){

        return taskService.getTask(taskListId,taskId).map(taskMapper::toDTO);

    }

    @PutMapping(path = "/{taskId}")
    public TaskDTO updateTask(

            @PathVariable("task_list_id")UUID taskListId,
            @PathVariable("taskId") UUID taskId,
            @RequestBody TaskDTO taskDTO){
        Task updatedTask = taskService.updateTask(
                taskListId,
                taskId,
                taskMapper.fromDTO(taskDTO)
        );

        return taskMapper.toDTO(updatedTask);
    }

    @DeleteMapping(path = "/{taskId}")
    void deleteTask(   @PathVariable("task_list_id")UUID taskListId,
                       @PathVariable("taskId") UUID taskId){
        taskService.deleteTask(taskListId,taskId);
    }

}
