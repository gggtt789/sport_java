package com.sport.sport.views;

import com.sport.sport.entities.Team;

import java.sql.Date;
import java.util.List;

public class TeamView {
    public Long id;
    public String name;
    public String sport;
    public Date birthday;
    public Boolean is_member;

    public List<EventView> events;
    public List<Long> events_ids;
    public List<Long> events_ids_on_delete;

    public List<MemberView> members;
    public List<Long> members_ids;

    public TeamView() {}
    public TeamView(Team team) {
        id = team.getId();
        name = team.getName();
        sport = team.getSport();
        birthday = team.getBirthday();
        is_member = team.isMember();
    }

}
