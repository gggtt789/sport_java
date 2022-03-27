package com.sport.sport.services;

import com.sport.sport.views.EventView;

import java.util.List;

public interface EventsService {
    List<EventView> getAll();
    EventView getEvent(Long id);
    void deleteEvent(Long id);
    void addEvent(EventView eventView);
    void updateEvent(Long id, EventView eventView);
}
