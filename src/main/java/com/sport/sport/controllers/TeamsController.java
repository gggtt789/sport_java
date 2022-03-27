package com.sport.sport.controllers;

import com.sport.sport.services.Impl.TeamsServiceImpl;
import com.sport.sport.services.TeamsService;
import com.sport.sport.views.TeamView;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamsController {
    private final TeamsService teamsService;

    public TeamsController() { this.teamsService = new TeamsServiceImpl(); }

    @DeleteMapping(value =  "/delete_team/{id}")
    public void deleteTeam(@PathVariable(name = "id") Long id) {
        teamsService.deleteTeam(id);
    }

    @PostMapping(value = "/add_team")
    public void addTeam(@RequestBody TeamView team) {
        teamsService.addTeam(team);
    }

    @PostMapping(value = "/update_team/{id}")
    public void updateTeam(@PathVariable Long id, @RequestBody TeamView team) {
        teamsService.updateTeam(id, team);
    }

    @GetMapping(value = "/get_team/{id}")
    public TeamView getTeam(@PathVariable Long id) {
        return teamsService.getTeam(id);
    }

    @GetMapping(value = "/get_teams")
    public List<TeamView> getTeams(
            @RequestParam String name,
            @RequestParam String sport,
            @RequestParam Long id,
            @RequestParam Date birthday
    ) {
        List<TeamView> teams = new ArrayList<>();
        for (TeamView teamView : teamsService.getAll()) {
            if (
                (name == null || name.equals(teamView.name)) &&
                (sport == null ||sport.equals(teamView.sport)) &&
                (id == null || id.equals(teamView.id)) &&
                (birthday == null || birthday.equals(teamView.birthday))
            ){
                teams.add(teamView);
            }
        }

        return teams;
    }
}
