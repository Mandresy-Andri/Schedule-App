package com.company.TaskManager.controllers;

import com.company.TaskManager.models.Event;
import com.company.TaskManager.repository.EventRepository;
import com.company.TaskManager.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") //allow CORS for my localhost
@RestController
public class EventController {

    @Autowired
    private ServiceLayer serviceLayer;

    @Autowired
    private EventRepository repo;

    //get event for current week
    //this is the main logic of the app so the operations will be handled by the Service Layer
    @RequestMapping(value="/events/weekly", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<List<Event>> getCurrentWeekEvents(){
        return serviceLayer.findEventsByWeek(null);
    }

    //get event by week
    //this is another main logic of the app so the operations will be handled by the Service Layer
    @RequestMapping(value="/events/weekly/{week}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<List<Event>> getEventByWeek(@PathVariable Date week){
        return serviceLayer.findEventsByWeek(week);
    }

    //get all event
    @RequestMapping(value="/events", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Event> getEvents(){ return repo.findAll(); }


    //get event by id
    @RequestMapping(value="/events/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Event getEventById(@PathVariable Long id){

        Optional<Event> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    //get event by Title
    @RequestMapping(value="/events/{title}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Event getEventById(@PathVariable String title){

        Optional<Event> returnVal = repo.findByTitle(title);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;        }
    }

    //add an event
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Event addEvent(@RequestBody Event event){ return repo.save(event); }

    //edit event
    @PutMapping("/events/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Event updateEvent(@RequestBody Event newEvent, @PathVariable long id) {
        return repo.findById(id)
                .map(event -> {
                    event.setTitle(newEvent.getTitle());
                    event.setEventDescription(newEvent.getEventDescription());
                    event.setLocation(newEvent.getLocation());
                    event.setEventDate(newEvent.getEventDate());
                    event.setStartTime(newEvent.getStartTime());
                    event.setEndTime(newEvent.getEndTime());
                    return repo.save(event);
                })
                .orElseGet(() -> {
                    newEvent.setId(id);
                    return repo.save(newEvent);
                });
    }

    //delete event
    @DeleteMapping("/events/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
