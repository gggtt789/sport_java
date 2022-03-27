package com.sport.sport.services;

import com.sport.sport.DAO.EventsDAO;
import com.sport.sport.DAO.MemberTeamsDAO;
import com.sport.sport.DAO.TeamsDAO;
import com.sport.sport.entities.Team;
import com.sport.sport.services.Impl.TeamsServiceImpl;
import com.sport.sport.views.TeamView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamsServiceTests {
    private TeamsDAO teamsDAO;
    private EventsDAO eventsDAO;
    private MemberTeamsDAO memberTeamsDAO;
    private TeamsService teamsService;

    @Before
    public void setUp() {
        teamsDAO = mock(TeamsDAO.class);
        eventsDAO = mock(EventsDAO.class);
        memberTeamsDAO = mock(MemberTeamsDAO.class);
        teamsService = new TeamsServiceImpl(teamsDAO, memberTeamsDAO, eventsDAO);
    }

    @Test
    public void getAllTeams() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1L, "name1", "sport1", new java.sql.Date(System.currentTimeMillis()), true));
        teams.add(new Team(2L, "name2", "sport2", new java.sql.Date(System.currentTimeMillis()), false));
        when(teamsDAO.getAll()).thenReturn(teams);

        List<TeamView> teamViews = teamsService.getAll();
        assertEquals(teamViews.size(), 2);
        assertEquals(teamViews.get(0).name, "name1");
        assertEquals(teamViews.get(1).sport, "sport2");
    }

    @Test
    public void deleteTeam() {
        Team team = new Team(1L, "name", "sport", null, true);
        when(teamsDAO.getById(any())).thenReturn(team);
        doNothing().when(teamsDAO).update(any());


        teamsService.deleteTeam(1L);
        verify(teamsDAO, times(1)).update(any());
        verify(teamsDAO, times(1)).getById(any());
    }


    @Test
    public void addTeam() {
        doNothing().when(teamsDAO).save(any());
        teamsService.addTeam(new TeamView());
        verify(teamsDAO, times(1)).save(any());
    }
}
