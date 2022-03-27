package com.sport.sport.controllers;

import com.sport.sport.views.TeamView;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TeamsControllerTest {
    private TeamsController teamsController = new TeamsController();

    @Test
    public void testOk() {
        assert(4 == teamsController.getTeams(null, null, null, null).size());

        TeamView teamView = new TeamView();
        teamView.name = "team";
        teamView.sport = "sport";
        teamView.is_member = false;
        teamView.birthday = Date.valueOf("2020-10-10");
        teamsController.addTeam(teamView);

        List<TeamView> teams = teamsController.getTeams(null, null, null, null);
        assert(5 == teams.size());

        TeamView teamView1 = teams.get(4);
        assertEquals(teamView1.name, "team");
        Long id = teamView1.id;

        List<TeamView> teams1 = teamsController.getTeams("team", null, null, null);
        assert(1 == teams1.size());


        TeamView teamView2 = new TeamView();
        teamView2.name = "new name";
        teamView2.sport = "new sport";
        teamView2.birthday = Date.valueOf("2022-10-10");
        teamView2.events_ids = new ArrayList<>();
        teamView2.events_ids.add(1L);
        teamsController.updateTeam(id, teamView2);

        TeamView teamView3 = teamsController.getTeam(id);
        assertEquals("new name", teamView3.name);
        assert(1 == teamView3.events_ids.size());
        assert(1L == teamView3.events_ids.get(0));

        TeamView teamView4 = new TeamView();
        teamView4.name = "name123";
        teamView4.events_ids_on_delete = new ArrayList<>();
        teamView4.events_ids_on_delete.add(1L);
        teamsController.updateTeam(id, teamView4);

        TeamView teamView5 = teamsController.getTeam(id);
        assertEquals("name123", teamView5.name);
        assert (1 == teamView5.events_ids.size());

        teamsController.deleteTeam(id);

        assert(4 == teamsController.getTeams(null, null, null, null).size());
    }
}
