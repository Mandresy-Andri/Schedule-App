package com.company.TaskManager.repository;

import com.company.TaskManager.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {

    Optional<Event> findByTitle(String title);

    Optional<List<Event>> findByEventDate(Date eventDay);

}
