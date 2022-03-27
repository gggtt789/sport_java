package com.sport.sport.views;

import com.sport.sport.entities.Event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class EventView {
    public Long id;
    public String name;
    public String venue;
    public String result;
    public LocalDateTime start_at;

    public BigDecimal min_price;
    public List<SeatView> seats;
    public List<Long> seats_ids;

    public String sport;
    public List<TeamView> teams;
    public List<Long> teams_ids;

    public List<Long> teams_ids_on_delete;

    public EventView() {}
    public EventView(Event event) {
        id = event.getId();
        name = event.getName();
        venue = event.getVenue();
        start_at = event.getStartAt();
    }
}
