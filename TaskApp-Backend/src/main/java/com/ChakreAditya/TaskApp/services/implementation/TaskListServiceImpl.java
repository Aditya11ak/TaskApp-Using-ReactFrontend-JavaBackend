package com.ChakreAditya.TaskApp.services.implementation;

import com.ChakreAditya.TaskApp.repository.TaskListRepository;
import com.ChakreAditya.TaskApp.services.TaskListService;
import com.ChakreAditya.TaskApp.tables.TaskList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    @Autowired
    TaskListRepository taskListRepository;



    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(taskList.getId() != null){
            throw new IllegalArgumentException("Task list with this Id already exists");
        }
        if(taskList.getTitle() == null || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task list title should be present");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(
                new TaskList(
                        null,
                        taskList.getTitle(),
                        taskList.getDescription(),
                        now,
                        now,
                        null
                )
        );
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    @Transactional
    public TaskList uptadeTaskList(UUID taskListId, TaskList taskList) {
        if(null == taskList.getId()){
            throw new IllegalArgumentException("Task list don't have an Id");
        }

        if(!Objects.equals(taskList.getId(),taskListId)){
            throw new IllegalArgumentException("Attempting to change taskList Id, Not permitted! ");
        }

        TaskList existingTaskList = taskListRepository.findById(taskListId)
                .orElseThrow(()->
                        new IllegalArgumentException("Task list not found!")
                );

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskLIst(UUID id) {
        taskListRepository.deleteById(id);

    }
}
