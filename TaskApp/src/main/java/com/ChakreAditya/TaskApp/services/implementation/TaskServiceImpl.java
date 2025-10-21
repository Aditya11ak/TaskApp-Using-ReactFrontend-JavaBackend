package com.ChakreAditya.TaskApp.services.implementation;

import com.ChakreAditya.TaskApp.domain.entities.TaskPriority;
import com.ChakreAditya.TaskApp.domain.entities.TaskStatus;
import com.ChakreAditya.TaskApp.repository.TaskListRepository;
import com.ChakreAditya.TaskApp.repository.TaskRepository;
import com.ChakreAditya.TaskApp.services.TaskService;
import com.ChakreAditya.TaskApp.tables.Task;
import com.ChakreAditya.TaskApp.tables.TaskList;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    @Transactional
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task already have ID!");
        }

        if(task.getTitle() == null || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task must have a title");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.PENDING;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(()
                        -> new IllegalArgumentException("Invalid task list Id provided !"));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                now,
                now,
                taskList

        );

        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId,taskId);
    }

    @Override
    @Transactional
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == taskId){
            throw new IllegalArgumentException("Task must have an Id");
        }

        if(!Objects.equals(taskId,task.getId())){
            throw new IllegalArgumentException("Task Id mismatch with the sent Id");
        }

        if(null == task.getPriority()){
            throw new IllegalArgumentException("Task must have valid priority");
        }

        if(null == task.getStatus()){
            throw new IllegalArgumentException("Task must have valid status");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(
                taskListId,
                taskId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setUpdated(LocalDateTime.now());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setDueDate(task.getDueDate());

        return taskRepository.save(existingTask);



    }

    @Override
    @Transactional
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
