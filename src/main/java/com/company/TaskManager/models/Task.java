package com.company.TaskManager.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "task_table")
public class Task implements Serializable {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "task_description")
    private String taskDescription;
    private String link;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE")
    private Date deadline;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Column(columnDefinition = "TIME", name = "task_time")
    private Time taskTime;

    public Task() {}

    public Task(String title, String taskDescription, String link, Date deadline, Time taskTime) {
        this.title=title;
        this.taskDescription=taskDescription;
        this.link=link;
        this.deadline=deadline;
        this.taskTime=taskTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Time getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Time taskTime) {
        this.taskTime = taskTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(taskDescription, task.taskDescription) && Objects.equals(link, task.link) && Objects.equals(deadline, task.deadline) && Objects.equals(taskTime, task.taskTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, taskDescription, link, deadline, taskTime);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", link='" + link + '\'' +
                ", deadline=" + deadline +
                ", taskTime=" + taskTime +
                '}';
    }
}

//JSON
/**
 {
 "title":"Watch Football Match 1",
 "taskDescription":"Football match against Barcelona",
 "link":"football.com",
 "deadline":"2023-02-12",
 "taskTime":"21:00:00"
 }
 */
