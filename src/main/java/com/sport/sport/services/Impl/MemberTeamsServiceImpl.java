package com.sport.sport.services.Impl;

import com.sport.sport.DAO.Impl.MemberTeamsDAOImpl;
import com.sport.sport.DAO.Impl.MembersDAOImpl;
import com.sport.sport.DAO.Impl.TeamsDAOImpl;
import com.sport.sport.DAO.MemberTeamsDAO;
import com.sport.sport.DAO.MembersDAO;
import com.sport.sport.DAO.TeamsDAO;
import com.sport.sport.entities.MemberTeam;
import com.sport.sport.services.MemberTeamsService;
import com.sport.sport.views.MemberTeamView;

import java.util.ArrayList;
import java.util.List;

public class MemberTeamsServiceImpl implements MemberTeamsService {
    private final MemberTeamsDAO memberTeamsDAO;
    private final MembersDAO membersDAO;
    private final TeamsDAO teamsDAO;

    public MemberTeamsServiceImpl() {
        this.memberTeamsDAO = new MemberTeamsDAOImpl();
        this.membersDAO = new MembersDAOImpl();
        this.teamsDAO = new TeamsDAOImpl();
    }

    @Override
    public List<MemberTeam> getAll() {
        return memberTeamsDAO.getAll();
    }

    @Override
    public List<MemberTeam> getAllByTeamId(Long team_id) {
        List<MemberTeam> memberTeams = new ArrayList<>();
        for (MemberTeam memberTeam : getAll()) {
            if (memberTeam.getTeam().getId().equals(team_id)) {
                memberTeams.add(memberTeam);
            }
        }
        return memberTeams;
    }

    @Override
    public List<MemberTeam> getAllByMemberId(Long member_id) {
        List<MemberTeam> memberTeams = new ArrayList<>();
        for (MemberTeam memberTeam : getAll()) {
            if (memberTeam.getMember().getId().equals(member_id)) {
                memberTeams.add(memberTeam);
            }
        }
        return memberTeams;
    }

    @Override
    public MemberTeam getById(Long id) {
        return memberTeamsDAO.getById(id);
    }

    @Override
    public void updateMemberTeam(Long id, MemberTeamView memberTeamView) {
        MemberTeam memberTeam = memberTeamsDAO.getById(id);

        if (memberTeamView.joined_at != null) {
            memberTeam.setJoinedAt(memberTeamView.joined_at);
        }
        if (memberTeamView.left_at != null) {
            memberTeam.setLeftAt(memberTeamView.left_at);
        }
        if (memberTeamView.role != null) {
            memberTeam.setRole(memberTeamView.role);
        }

        memberTeamsDAO.update(memberTeam);
    }

    @Override
    public void deleteMemberTeam(Long id) {
        MemberTeam memberTeam = memberTeamsDAO.getById(id);
        memberTeamsDAO.delete(memberTeam);
    }

    @Override
    public void addMemberTeam(MemberTeamView memberTeamView) {
        MemberTeam memberTeam = new MemberTeam();
        memberTeam.setJoinedAt(memberTeamView.joined_at);
        memberTeam.setLeftAt(memberTeamView.left_at);
        memberTeam.setRole(memberTeamView.role);
        memberTeam.setMember(membersDAO.getById(memberTeamView.member.id));
        memberTeam.setTeam(teamsDAO.getById(memberTeamView.team.id));
        memberTeamsDAO.save(memberTeam);
    }
}
