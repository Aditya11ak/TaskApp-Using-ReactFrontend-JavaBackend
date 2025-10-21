package com.ChakreAditya.TaskApp.services;

import com.ChakreAditya.TaskApp.tables.TaskList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface TaskListService {

    List<TaskList> listTaskLists() ;
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID id);
    TaskList uptadeTaskList(UUID taskListId, TaskList taskList);
    void deleteTaskLIst(UUID id);
}
