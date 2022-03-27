package com.sport.sport.controllers;

import com.sport.sport.views.EventView;
import com.sport.sport.services.EventsService;
import com.sport.sport.services.Impl.EventsServiceImpl;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {
    private final EventsService eventsService;

    public EventsController() {
        this.eventsService = new EventsServiceImpl();
    }

    @DeleteMapping(value =  "/delete_event/{id}")
    public void deleteEvent(@PathVariable(name = "id") Long id) {
        eventsService.deleteEvent(id);
    }

    @PostMapping(value =  "/add_event")
    public void addEvent(@RequestBody EventView eventView) {
        eventsService.addEvent(eventView);
    }

    @GetMapping(value = "/get_event/{id}")
    public EventView getEvent(@PathVariable Long id) {
        return eventsService.getEvent(id);
    }

    @PostMapping(value = "/update_event/{id}")
    public void updateEvent(
            @PathVariable Long id,
            @RequestBody EventView eventView)
    {
        eventsService.updateEvent(id, eventView);
    }

    @GetMapping(value =  "/get_events")
    public List<EventView> getEvents(
            @RequestParam(required = false) String venue,
            @RequestParam(required = false) String sport,
            @RequestParam(required = false, value = "start_at") String start_at_str
    ) {
        LocalDateTime start_at = null;
        if (start_at_str != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            start_at = LocalDateTime.parse(start_at_str, formatter);
        }

        List<EventView> events = new ArrayList<>();
        for (EventView event : eventsService.getAll()) {
            if (
                (sport == null || sport.equals(event.sport)) &&
                (venue == null || venue.equals(event.venue)) &&
                (start_at == null || start_at.compareTo(event.start_at) < 0)
            ){
                events.add(event);
            }
        }
        return events;
    }
}
