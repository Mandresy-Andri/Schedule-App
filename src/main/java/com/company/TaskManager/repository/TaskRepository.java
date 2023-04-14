package com.company.TaskManager.repository;

import com.company.TaskManager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Optional<Task> findByTitle(String title);

    Optional<List<Task>> findByDeadline(Date deadline);

}
