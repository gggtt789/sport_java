package com.sport.sport.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Events", schema = "public")
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name") private String name;
    @Column(name = "venue") private String venue;
    @Column(name = "result") private String result;
    @Column(name = "start_at") private LocalDateTime start_at;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Seat> seats;

    @ManyToMany(mappedBy = "events")
    private List<Team> teams;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getVenue() { return venue; }
    public String getResult() { return result; }
    public LocalDateTime getStartAt() { return start_at; }
    public List<Seat> getSeats() { return seats; }
    public List<Team> getTeams() { return teams; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setVenue(String venue) { this.venue = venue; }
    public void setResult(String result) { this.result = result; }
    public void setStartAt(LocalDateTime start_at) { this.start_at = start_at; }
    public void setSeats(List<Seat> seats) { this.seats = seats; }
    public void setTeams(List<Team> teams) { this.teams = teams; }

    public void removeSeat(Seat seat) { seats.remove(seat); }
    public void addSeat(Seat seat) {
        if (seats == null) {
            seats = new ArrayList<>();
        }
        seats.add(seat);
    }

    public void removeTeam(Team team) { teams.remove(team); }
    public void addTeam(Team team) {
        if (teams == null) {
            teams = new ArrayList<>();
        }
        teams.add(team);
    }

    public Event() {}
    public Event(Long id, String name, String venue, String result, LocalDateTime start_at)
    {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.result = result;
        this.start_at = start_at;
    }
    public Event(String name, String venue, String result, LocalDateTime start_at)
    {
        this.name = name;
        this.venue = venue;
        this.result = result;
        this.start_at = start_at;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Event e = (Event) o;
        return id.equals(e.id) && name.equals(e.name) && venue.equals(e.name) &&
                result.equals(e.result) && start_at.equals(e.start_at) &&
                seats.equals(e.seats) && teams.equals(e.teams);
    }
}

