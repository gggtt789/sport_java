package com.sport.sport.controllers;

import com.sport.sport.entities.MemberTeam;
import com.sport.sport.views.MemberTeamView;
import com.sport.sport.views.MemberView;
import com.sport.sport.views.TeamView;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MemberTeamsControllerTest {
    private final MemberTeamsController memberTeamsController = new MemberTeamsController();

    @Test
    public void testOk() {
        assert(5 == memberTeamsController.getMemberTeams().size());

        MemberTeamView memberTeamView = new MemberTeamView();
        memberTeamView.joined_at = Date.valueOf("2010-10-10");
        memberTeamView.left_at = Date.valueOf("2012-10-10");
        memberTeamView.role = "role";
        memberTeamView.member = new MemberView();
        memberTeamView.member.id = 1L;
        memberTeamView.team = new TeamView();
        memberTeamView.team.id = 1L;
        memberTeamsController.addMemberTeam(memberTeamView);

        List<MemberTeamView> memberTeams = memberTeamsController.getMemberTeams();
        assert(6 == memberTeams.size());
        MemberTeamView memberTeamView1 = memberTeams.get(5);
        Long id = memberTeamView1.id;

        MemberTeamView memberTeamView2 = new MemberTeamView();
        memberTeamView2.joined_at = Date.valueOf("2020-10-10");
        memberTeamView2.left_at = Date.valueOf("2022-10-10");
        memberTeamView2.role = "role2";
        memberTeamsController.updateMemberTeam(id, memberTeamView2);

        MemberTeam memberTeam = memberTeamsController.getMemberTeam(id);
        assertEquals("role2", memberTeam.getRole());

        memberTeamsController.deleteMemberTeam(id);

        assert(5 == memberTeamsController.getMemberTeams().size());
    }

    @Test
    public void getMemberTeamsByTeam() {
        assert(2 == memberTeamsController.getMemberTeamsByTeam(1L).size());
        assert(2 == memberTeamsController.getMemberTeamsByMember(1L).size());
    }
}
