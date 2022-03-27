package com.sport.sport.services;

import com.sport.sport.entities.MemberTeam;
import com.sport.sport.views.MemberTeamView;

import java.util.List;

public interface MemberTeamsService {
    List<MemberTeam> getAll();
    List<MemberTeam> getAllByTeamId(Long id);
    List<MemberTeam> getAllByMemberId(Long id);
    MemberTeam getById(Long id);
    void deleteMemberTeam(Long id);
    void addMemberTeam(MemberTeamView memberTeamView);
    void updateMemberTeam(Long id, MemberTeamView memberTeamView);
}
