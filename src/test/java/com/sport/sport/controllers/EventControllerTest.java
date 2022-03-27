package com.sport.sport.controllers;

import com.sport.sport.views.EventView;
import com.sport.sport.views.SeatView;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventControllerTest {
    private EventsController eventsController = new EventsController();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Test
    public void testOk() {
        assert (2 == eventsController.getEvents(null, null, null).size());

        EventView eventView = new EventView();
        eventView.name = "event3";
        eventView.venue = "venue3";
        eventView.result = "in progress";
        eventView.start_at = LocalDateTime.parse("2020-10-10T12:12:12", formatter);
        eventsController.addEvent(eventView);


        List<EventView> events = eventsController.getEvents(null, null, null);
        assert (events.size() == 3);

        EventView eventView1 = events.get(2);
        Long id = eventView1.id;
        assertEquals(eventView1.name, "event3");


        EventView eventView2 = new EventView();
        eventView2.name = "event4";
        eventView2.venue = "venue4";
        eventView2.result = "result";
        eventView2.start_at = LocalDateTime.parse("2020-10-10T12:12:13", formatter);
        SeatView seatView = new SeatView();
        seatView.name = "seat_name";
        seatView.total_number = 12L;
        seatView.available_number = 12L;
        seatView.price = new BigDecimal(123L);
        eventView2.seats = new ArrayList<>();
        eventView2.seats.add(seatView);
        eventsController.updateEvent(id, eventView2);


        EventView eventView3 = eventsController.getEvent(id);
        assertEquals(eventView3.name, "event4");


        eventsController.deleteEvent(id);
        assert (eventsController.getEvents(null, null, null).size() == 2);

        assert (eventsController.getEvents("venue1", null, null).size() == 1);
        assert (eventsController.getEvents(null, "sport1", null).size() == 1);
        assert (eventsController.getEvents(null, null, "2015-10-10T12:12:12").size() == 1);
    }

}
