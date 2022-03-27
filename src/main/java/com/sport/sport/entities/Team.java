package com.sport.sport.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Teams", schema = "public")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name") private String name;
    @Column(name = "sport") private String sport;
    @Column(name = "birthday") private Date birthday;
    @Column(name = "is_member") private Boolean is_member;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "event_team",
            joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemberTeam> members_teams;

    public Team() {}
    public Team(Long id, String name, String sport, Date birthday, Boolean is_member) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.birthday = birthday;
        this.is_member = is_member != null ? is_member : false;
    }
    public Team(String name, String sport, Date birthday, Boolean is_member) {
        this.name = name;
        this.sport = sport;
        this.birthday = birthday;
        this.is_member = is_member != null ? is_member : false;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSport() { return sport; }
    public Date getBirthday() { return birthday; }
    public Boolean isMember() { return is_member; }
    public List<Event> getEvents() { return events; }

    public void setName(String name) { this.name = name; }
    public void setSport(String sport) { this.sport = sport; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public void setEvents(List<Event> events) { this.events = events; }


    public void removeEvent(Event event) {
        if (events != null) {
            events.remove(event);
        }
    }
    public void addEvent(Event event) {
        if (events == null) {
            events = new ArrayList<>();
        }
        events.add(event);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Team t = (Team) o;
        return id == t.id && name.equals(t.name) && sport.equals(t.sport) &&
                birthday.equals(t.birthday) && is_member == t.is_member &&
                events.equals(t.events);
    }
}
