package com.sport.sport.services.Impl;

import com.sport.sport.DAO.EventsDAO;
import com.sport.sport.DAO.Impl.EventsDAOImpl;
import com.sport.sport.DAO.Impl.SeatsDAOImpl;
import com.sport.sport.DAO.Impl.TeamsDAOImpl;
import com.sport.sport.DAO.SeatsDAO;
import com.sport.sport.DAO.TeamsDAO;
import com.sport.sport.entities.Event;
import com.sport.sport.entities.Seat;
import com.sport.sport.entities.Team;
import com.sport.sport.views.EventView;
import com.sport.sport.views.SeatView;
import com.sport.sport.views.TeamView;
import com.sport.sport.services.EventsService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventsServiceImpl implements EventsService {
    private final EventsDAO eventsDAO;
    private final TeamsDAO teamsDAO;
    private final SeatsDAO seatsDAO;

    public EventsServiceImpl() {
        this.eventsDAO = new EventsDAOImpl();
        this.teamsDAO = new TeamsDAOImpl();
        this.seatsDAO = new SeatsDAOImpl();
    }

    public EventsServiceImpl(EventsDAO eventsDAO, TeamsDAO teamsDAO, SeatsDAO seatsDAO) {
        this.eventsDAO = eventsDAO;
        this.teamsDAO = teamsDAO;
        this.seatsDAO = seatsDAO;
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventsDAO.getById(id);
        eventsDAO.delete(event);
    }

    @Override
    public void addEvent(EventView eventView) {
        Event event = new Event(eventView.name, eventView.venue,
                eventView.result, eventView.start_at);
        eventsDAO.save(event);
    }

    @Override
    public void updateEvent(Long id, EventView eventView) {
        Event event = eventsDAO.getById(id);

        if (eventView.name != null) {
            event.setName(eventView.name);
        }
        if (eventView.venue != null) {
            event.setVenue(eventView.venue);
        }
        if (eventView.result != null) {
            event.setResult(eventView.result);
        }
        if (eventView.start_at != null) {
            event.setStartAt(eventView.start_at);
        }
        if (eventView.seats_ids != null && eventView.seats_ids.size() > 0) {
            // remove
            for (Seat seat : event.getSeats()) {
                if (eventView.seats_ids.contains(seat.getId())) {
                    event.removeSeat(seat);
                    seat.setEvent(null);
                    seatsDAO.update(seat);
                }
            }
        }
        if (eventView.seats != null && eventView.seats.size() > 0) {
            // add new
            for (SeatView seatView : eventView.seats) {
                Seat seat = new Seat(seatView.name, seatView.available_number,
                        seatView.total_number, seatView.price);
                seat.setEvent(event);
                event.addSeat(seat);
                seatsDAO.save(seat);
            }
        }
        if (eventView.teams_ids != null) {
            for (Long team_id : eventView.teams_ids) {
                Team team = teamsDAO.getById(team_id);
                if (!event.getTeams().contains(team)) {
                    event.addTeam(team);
                    team.addEvent(event);
                }
            }
        }
        if (eventView.teams_ids_on_delete != null) {
            for (Long  team_id : eventView.teams_ids_on_delete) {
                event.removeTeam(teamsDAO.getById(team_id));
            }
        }
        eventsDAO.update(event);
    }

    @Override
    public EventView getEvent(Long id) {
        Event event = eventsDAO.getById(id);

        EventView eventView = new EventView(event);

        if (event.getSeats() != null && event.getSeats().size() > 0) {
            BigDecimal min_price = event.getSeats().get(0).getPrice();
            eventView.seats = new ArrayList<>();
            eventView.seats_ids = new ArrayList<>();
            for (Seat seat : event.getSeats()) {
                eventView.seats.add(new SeatView(seat));
                eventView.seats_ids.add(seat.getId());

                // min_price > seat.getPrice()
                if (min_price.compareTo(seat.getPrice()) > 0) {
                    min_price = seat.getPrice();
                }
            }
            eventView.min_price = min_price;
        }

        if (event.getTeams() != null && event.getTeams().size() > 0) {
            eventView.sport = event.getTeams().get(0).getSport();

            eventView.teams = new ArrayList<>();
            eventView.teams_ids = new ArrayList<>();
            for (Team team : event.getTeams()) {
                eventView.teams_ids.add(team.getId());
                eventView.teams.add(new TeamView(team));
            }
        }

        return eventView;
    }

    @Override
    public List<EventView> getAll() {
        List<EventView> eventsTemplates = new ArrayList<>();
        for (Event event : eventsDAO.getAll()) {
            EventView eventView = getEvent(event.getId());
            eventsTemplates.add(eventView);
        }
        return eventsTemplates;
    }
}
