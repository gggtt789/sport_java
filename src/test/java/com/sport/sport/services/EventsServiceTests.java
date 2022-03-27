package com.sport.sport.services;

import com.sport.sport.DAO.EventsDAO;
import com.sport.sport.DAO.SeatsDAO;
import com.sport.sport.DAO.TeamsDAO;
import com.sport.sport.entities.Event;
import com.sport.sport.entities.Team;
import com.sport.sport.services.Impl.EventsServiceImpl;
import com.sport.sport.views.EventView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class EventsServiceTests {
    private EventsDAO eventsDAO;
    private TeamsDAO teamsDAO;
    private SeatsDAO seatsDAO;
    private EventsService eventsService;

    @Before
    public void setUp() {
        eventsDAO = mock(EventsDAO.class);
        teamsDAO = mock(TeamsDAO.class);
        seatsDAO = mock(SeatsDAO.class);
        eventsService = new EventsServiceImpl(eventsDAO, teamsDAO, seatsDAO);
    }

//
//    @Test
//    public void getAllEvents() {
//        List<Event> events = new ArrayList<>();
//        Event event1 = new Event(1L, "name1", "venue1", "result1", null);
//        Event event2 = new Event(2L, "name2", "venue2", "result2", null);
//        events.add(event1);
//        events.add(event2);
//        when(eventsDAO.getAll()).thenReturn(events);
//        when(eventsDAO.getById(1L)).thenReturn(event1);
//        when(eventsDAO.getById(2L)).thenReturn(event2);
//
//        assertEquals(eventsService.getAll().size(), 2);
//        assert(eventsService.getAll().get(0).id == 1L);
//        assertEquals(eventsService.getAll().get(0).name, "name1");
//        assertEquals(eventsService.getAll().get(1).venue, "venue2");
//    }
//

    @Test
    public void deleteEvent() {
        Event event = new Event(1L, "name8", "venue8", "result8", null);
        when(eventsDAO.getById(any())).thenReturn(event);
        doNothing().when(eventsDAO).delete(any());

        eventsService.deleteEvent(1L);
        verify(eventsDAO, times(1)).delete(any());
        verify(eventsDAO, times(1)).getById(any());
    }

    @Test
    public void addEvent() {
        EventView eventView = new EventView();

        doNothing().when(eventsDAO).save(any());
        eventsService.addEvent(eventView);
        verify(eventsDAO, times(1)).save(any());
    }

    @Test
    public void updateEvent() {
        Event event = new Event(1L, "name1", null, null, null);
        when(eventsDAO.getById(any())).thenReturn(event);
        doNothing().when(eventsDAO).update(any());

        EventView eventView = new EventView();
        eventView.name = "name2";

        eventsService.updateEvent(1L, eventView);
        verify(eventsDAO, times(1)).getById(any());
        verify(teamsDAO, times(0)).getById(any());
    }

    @Test
    public void updateEventWithTeam() {
        List<Team> teams = new ArrayList<>();
        Team team1 = new Team(1L, null, null,null, null);
        Team team2 = new Team(2L, null, null,null, null);
        teams.add(team1);
        teams.add(team2);

        Event event = new Event(1L, null, null, null, null);
        event.setTeams(teams);

        when(eventsDAO.getById(any())).thenReturn(event);
        doNothing().when(eventsDAO).update(any());
        when(teamsDAO.getById(any())).thenReturn(team1);

        EventView eventView = new EventView();
        List<Long> teams_ids = new ArrayList<>();
        teams_ids.add(1L);
        eventView.teams_ids = teams_ids;

        eventsService.updateEvent(1L, eventView);
        verify(eventsDAO, times(1)).getById(any());
        verify(teamsDAO, times(1)).getById(any());
    }
}
