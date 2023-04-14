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
@Table(name = "event_table")
public class Event implements Serializable {
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "event_description")
    private String eventDescription;
    private String location;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "event_date")
    private Date eventDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Column(columnDefinition = "TIME", name = "start_time")
    private Time startTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Column(columnDefinition = "TIME", name = "end_time")
    private Time endTime;

    public Event() {}

    public Event(String title, String eventDescription, String location, Date eventDate, Time startTime, Time endTime) {
        this.title=title;
        this.eventDescription=eventDescription;
        this.location=location;
        this.eventDate=eventDate;
        this.startTime=startTime;
        this.endTime=endTime;
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

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(title, event.title) && Objects.equals(eventDescription, event.eventDescription) && Objects.equals(location, event.location) && Objects.equals(eventDate, event.eventDate) && Objects.equals(startTime, event.startTime) && Objects.equals(endTime, event.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, eventDescription, location, eventDate, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", location='" + location + '\'' +
                ", eventDate=" + eventDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

//JSON
/** {
 "title":"ds",
 "eventDescription":"dsda",
 "location":"sada",
 "eventDate":"2023-03-15",
 "startTime":"15:00:00",
 "endTime":"16:00:00"
 }
 */
