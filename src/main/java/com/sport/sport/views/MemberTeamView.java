package com.sport.sport.views;

import com.sport.sport.entities.MemberTeam;

import java.sql.Date;

public class MemberTeamView {
    public Long id;
    public Date joined_at;
    public Date left_at;
    public String role;

    public MemberView member;
    public TeamView team;

    public MemberTeamView() {}
    public MemberTeamView(MemberTeam memberTeam) {
        id = memberTeam.getId();
        joined_at = memberTeam.getJoinedAt();
        left_at = memberTeam.getLeftAt();
        role = memberTeam.getRole();
    }
}
