package com.sport.sport.views;

import com.sport.sport.entities.Member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MemberView {
    public Long id;
    public String name;
    public Date birthday;

    public String role;
    public TeamView current_team;

    public List<EventView> events_templates;
    public List<Long> events_ids;

    public List<Long> prev_teams_ids;
    List<TeamView> prev_teams;

    public MemberView() {}
    public MemberView(Member member) {
        id = member.getId();
        name = member.getName();
        birthday = member.getBirthday();
    }

    public void addPrevTeam(TeamView team) {
        if (prev_teams == null) {
            prev_teams = new ArrayList<>();
            prev_teams_ids = new ArrayList<>();
        }
        prev_teams.add(team);
        prev_teams_ids.add(team.id);
    }
}
