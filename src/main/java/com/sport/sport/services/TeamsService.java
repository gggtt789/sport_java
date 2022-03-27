package com.sport.sport.services;

import com.sport.sport.views.TeamView;

import java.util.List;

public interface TeamsService {
    List<TeamView> getAll();
    TeamView getTeam(Long id);
    void deleteTeam(Long id);
    void addTeam(TeamView teamView);
    void updateTeam(Long id, TeamView teamView);
}
