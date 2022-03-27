package com.sport.sport.controllers;

import com.sport.sport.entities.MemberTeam;
import com.sport.sport.services.Impl.MemberTeamsServiceImpl;
import com.sport.sport.services.MemberTeamsService;
import com.sport.sport.views.MemberTeamView;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member_teams")
public class MemberTeamsController {
    private final MemberTeamsService memberTeamsService;

    public MemberTeamsController() { memberTeamsService = new MemberTeamsServiceImpl(); }

    @DeleteMapping(value =  "/delete_member_team/{id}")
    public void deleteMemberTeam(@PathVariable Long id) {
        memberTeamsService.deleteMemberTeam(id);
    }

    @PostMapping(value =  "/add_member_team")
    public void addMemberTeam(@RequestBody MemberTeamView memberTeamView) {
        memberTeamsService.addMemberTeam(memberTeamView);
    }

    @PostMapping(value =  "/update_member_team/{id}")
    public void updateMemberTeam(@PathVariable Long id, @RequestBody MemberTeamView memberTeamView) {
        memberTeamsService.updateMemberTeam(id, memberTeamView);
    }

    @GetMapping(value =  "/get_member_team/{id}")
    public MemberTeam getMemberTeam(@PathVariable Long id) {
        return memberTeamsService.getById(id);
    }

    @GetMapping(value =  "/get_member_teams")
    public List<MemberTeamView> getMemberTeams() {
        List<MemberTeamView> memberTeamViews = new ArrayList<>();
        for (MemberTeam memberTeam : memberTeamsService.getAll()) {
            memberTeamViews.add(new MemberTeamView(memberTeam));
        }
        return memberTeamViews;
    }

    @GetMapping(value =  "/get_member_teams_by_team/{id}")
    public List<MemberTeamView> getMemberTeamsByTeam(@PathVariable Long id) {
        List<MemberTeamView> memberTeamViews = new ArrayList<>();
        for (MemberTeam memberTeam : memberTeamsService.getAllByTeamId(id)) {
            memberTeamViews.add(new MemberTeamView(memberTeam));
        }
        return memberTeamViews;
    }

    @GetMapping(value =  "/get_member_teams_by_member/{id}")
    public List<MemberTeamView> getMemberTeamsByMember(@PathVariable Long id) {
        List<MemberTeamView> memberTeamViews = new ArrayList<>();
        for (MemberTeam memberTeam : memberTeamsService.getAllByMemberId(id)) {
            memberTeamViews.add(new MemberTeamView(memberTeam));
        }
        return memberTeamViews;
    }
}
