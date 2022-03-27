package com.sport.sport.services.Impl;

import com.sport.sport.DAO.EventsDAO;
import com.sport.sport.DAO.Impl.EventsDAOImpl;
import com.sport.sport.DAO.Impl.MemberTeamsDAOImpl;
import com.sport.sport.DAO.Impl.TeamsDAOImpl;
import com.sport.sport.DAO.MemberTeamsDAO;
import com.sport.sport.DAO.TeamsDAO;
import com.sport.sport.entities.Event;
import com.sport.sport.entities.MemberTeam;
import com.sport.sport.entities.Team;
import com.sport.sport.services.TeamsService;
import com.sport.sport.views.EventView;
import com.sport.sport.views.TeamView;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamsServiceImpl implements TeamsService {
    private final TeamsDAO teamsDAO;
    private final EventsDAO eventsDAO;
    private final MemberTeamsDAO memberTeamsDAO;

    public TeamsServiceImpl() {
        this.teamsDAO = new TeamsDAOImpl();
        this.memberTeamsDAO = new MemberTeamsDAOImpl();
        this.eventsDAO = new EventsDAOImpl();
    }
    public TeamsServiceImpl(TeamsDAO teamsDAO, MemberTeamsDAO memberTeamsDAO, EventsDAO eventsDAO) {
        this.teamsDAO = teamsDAO;
        this.eventsDAO = eventsDAO;
        this.memberTeamsDAO = memberTeamsDAO;
    }

    @Override
    public void deleteTeam(Long id) {
        Team team = teamsDAO.getById(id);
        team.setName("Данные удалены");
        teamsDAO.update(team);
    }

    @Override
    public void addTeam(TeamView teamView) {
        Team team = new Team(teamView.name, teamView.sport,
                teamView.birthday, teamView.is_member);
        teamsDAO.save(team);
    }

    @Override
    public void updateTeam(Long id, TeamView teamView) {
        Team team = teamsDAO.getById(id);

        if (teamView.name != null) {
            team.setName(teamView.name);
        }
        if (teamView.sport != null) {
            team.setSport(teamView.sport);
        }
        if (teamView.birthday != null) {
            team.setBirthday(teamView.birthday);
        }
        if (teamView.events_ids_on_delete != null) { // && teamView.events_ids_on_delete.size() > 0) {
            for (Long event_id : teamView.events_ids_on_delete) {
                Event event = eventsDAO.getById(event_id);
                event.removeTeam(team);
                eventsDAO.update(event);
                team.removeEvent(event);
            }
        }
        if (teamView.events_ids != null && teamView.events_ids.size() > 0) {
            for (Long event_id : teamView.events_ids) {
                Event event = eventsDAO.getById(event_id);
                team.addEvent(event);
            }
        }

        teamsDAO.update(team);
    }

    @Override
    public TeamView getTeam(Long id) {
        Team team = teamsDAO.getById(id);

        TeamView teamView = new TeamView(team);

        teamView.events = new ArrayList<>();
        teamView.events_ids = new ArrayList<>();
        for (Event event : team.getEvents()) {
            teamView.events.add(new EventView(event));
            teamView.events_ids.add(event.getId());
        }

        teamView.members = new ArrayList<>();
        teamView.members_ids = new ArrayList<>();
        for (MemberTeam memberTeam : memberTeamsDAO.getAll()) {
            if (memberTeam.getTeam().getId().equals(id)) {

            }
        }

        return teamView;
    }

    @Override
    public List<TeamView> getAll() {
        List<TeamView> teamsTemplates = new ArrayList<>();
        for (Team team : teamsDAO.getAll()) {
            if (team.getName().compareTo("Данные удалены") != 0) {
                teamsTemplates.add(new TeamView(team));
            }
        }
        return teamsTemplates;
    }
}
