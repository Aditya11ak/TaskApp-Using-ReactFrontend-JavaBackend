package com.ChakreAditya.TaskApp.services;

import com.ChakreAditya.TaskApp.tables.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface TaskService {
    List<Task> listTasks(UUID taskListId);

    Task createTask(UUID taskListId, Task task);

    Optional<Task> getTask(UUID taskListId, UUID taskId);

    Task updateTask(UUID taskListId, UUID taskId, Task task);

    void deleteTask(UUID taskListId, UUID taskId);
}
