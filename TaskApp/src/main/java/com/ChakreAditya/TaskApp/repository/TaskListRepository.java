package com.ChakreAditya.TaskApp.repository;


import com.ChakreAditya.TaskApp.tables.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
}
