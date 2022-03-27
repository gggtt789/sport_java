package com.sport.sport.DAO;

import com.sport.sport.entities.Event;

import java.util.List;

public interface EventsDAO {
    Event getById(Long objectId);
    List<Event> getAll();
    void save(Event object);
    void update(Event object);
    void delete(Event object);
}
