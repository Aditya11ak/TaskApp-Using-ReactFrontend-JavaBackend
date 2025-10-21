package com.ChakreAditya.TaskApp.controllers;

import com.ChakreAditya.TaskApp.domain.dto.TaskListDTO;
import com.ChakreAditya.TaskApp.mappers.TaskListMapper;
import com.ChakreAditya.TaskApp.services.TaskListService;
import com.ChakreAditya.TaskApp.tables.TaskList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;
    @Autowired
    private TaskListMapper taskListMapper;


    @GetMapping
    public List<TaskListDTO> ListTaskList(){
        return taskListService.listTaskLists().stream()
                .map(taskListMapper::toDTO)// taskList -> taskListMapper.toDTO(taskList) this is what happened.
                .toList();
    }

    @PostMapping
    public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO){
        return taskListMapper.toDTO(
                taskListService.createTaskList(
                        taskListMapper.
                                fromDTO(taskListDTO)
                )
        );
    }
    @GetMapping("/{id}")
    public Optional<TaskListDTO> getTaskList(@PathVariable("task_list_id") UUID taskListId){
        return taskListService.getTaskList(taskListId).map(taskListMapper::toDTO);
    }

    @PutMapping(path = "/{task_list_id}")
    public TaskListDTO updateTaskList(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskListDTO taskListDTO){
        TaskList updatedTaskList = taskListService
                .uptadeTaskList(taskListId,
                taskListMapper.fromDTO(taskListDTO));

        return taskListMapper.toDTO(updatedTaskList);

    }

    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskListById( @PathVariable("task_list_id") UUID taskLIstId){
        taskListService.deleteTaskLIst(taskLIstId);
    }

}
