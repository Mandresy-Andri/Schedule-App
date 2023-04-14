package com.company.TaskManager.service;

import com.company.TaskManager.models.Event;
import com.company.TaskManager.models.Task;
import com.company.TaskManager.repository.EventRepository;
import com.company.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {
    @Autowired
    private EventRepository eventRepository;
    private TaskRepository taskRepository;
    @Autowired
    public ServiceLayer(EventRepository eventRepository, TaskRepository taskRepository) {
        this.eventRepository=eventRepository;
        this.taskRepository=taskRepository;
    }

    public List<List<Event>> findEventsByWeek(Date week) {
        //if week passed is null then return events of current week
        LocalDateTime today;
        if(week==null)
            today = LocalDateTime.now();
        //if not then convert that Date to localDateTime for its methods that allow us to find the monday of that week
        else {
            LocalDate localDate = week.toLocalDate();
            today = localDate.atStartOfDay();
        }

        //get the current week, then get the first day (monday) of that week
        LocalDateTime mondayOfCurrentWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        List<List<Event>> list = new ArrayList<List<Event>>();

        //get the events of each day starting from the first day
        for(int i=0;i<7;i++){
            //convert LocalDateTime to sql.Date since that is the type of our variable
            today = mondayOfCurrentWeek.plusDays(i); //we get the monday and add one day each loop
            java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(today); //convert to sql.timestamp
            java.sql.Date sqlDate = new java.sql.Date(timestamp.getTime());//convert timestamp to sql.Date

            List<Event> events = getEventByDay(sqlDate);
            list.add(events);
        }

        return list;
    }

    //get event by day
    public List<Event> getEventByDay(Date day){
        Optional<List<Event>> returnVal = eventRepository.findByEventDate(day);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    public List<List<Task>> findTasksByWeek(Date week) {
        //if week passed is null then return events of current week
        LocalDateTime today;
        if(week==null)
            today = LocalDateTime.now();
            //if not then convert that Date to localDateTime for its methods that allow us to find the monday of that week
        else {
            LocalDate localDate = week.toLocalDate();
            today = localDate.atStartOfDay();
        }

        //get the current week, then get the first day (monday) of that week
        LocalDateTime mondayOfCurrentWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        List<List<Task>> list = new ArrayList<List<Task>>();

        //get the events of each day starting from the first day
        for(int i=0;i<7;i++){
            //convert LocalDateTime to sql.Date since that is the type of our variable
            today = mondayOfCurrentWeek.plusDays(i); //we get the monday and add one day each loop
            java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(today); //convert to sql.timestamp
            java.sql.Date sqlDate = new java.sql.Date(timestamp.getTime());//convert timestamp to sql.Date

            List<Task> tasks = getTaskByDay(sqlDate);
            list.add(tasks);
        }

        return list;
    }

    //get task by day
    public List<Task> getTaskByDay(Date day){
        Optional<List<Task>> returnVal = taskRepository.findByDeadline(day);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }
}
