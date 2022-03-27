package com.sport.sport.services.Impl;

import com.sport.sport.DAO.Impl.MembersDAOImpl;
import com.sport.sport.DAO.MembersDAO;
import com.sport.sport.entities.Member;
import com.sport.sport.entities.MemberTeam;
import com.sport.sport.services.MembersService;
import com.sport.sport.views.MemberView;
import com.sport.sport.views.TeamView;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MembersServiceImpl implements MembersService {
    private final MembersDAO membersDAO;

    public MembersServiceImpl() {
        membersDAO = new MembersDAOImpl();
    }
    public MembersServiceImpl(MembersDAO membersDAO) {
        this.membersDAO = membersDAO;
    }

    @Override
    public void deleteMember(Long id) {
        Member member = membersDAO.getById(id);
        member.setName("Данные удалены");
        membersDAO.update(member);
    }

    @Override
    public void addMember(MemberView memberView) {
        Member member = new Member(memberView.name, memberView.birthday);
        membersDAO.save(member);
    }

    @Override
    public MemberView getMember(Long id) {
        Member member = membersDAO.getById(id);
        MemberView memberView = new MemberView(member);

        if (member.getMemberTeams() != null) {
            for (MemberTeam memberTeam : member.getMemberTeams()) {
                TeamView teamView = new TeamView(memberTeam.getTeam());
                if (memberTeam.getLeftAt() == null) {
                    memberView.current_team = teamView;
                    memberView.role = memberTeam.getRole();
                } else {
                    memberView.addPrevTeam(teamView);
                }
            }
        }

        return memberView;
    }

    @Override
    public List<MemberView> getAll() {
        List<MemberView> membersTemplates = new ArrayList<>();
        for (Member member : membersDAO.getAll()) {
            if (member.getName().compareTo("Данные удалены") != 0) {
                membersTemplates.add(getMember(member.getId()));
            }
        }
        return  membersTemplates;
    }

    @Override
    public void updateMember(Long id, MemberView memberView) {
        Member member = membersDAO.getById(id);

        if (memberView.name != null) {
            member.setName(memberView.name);
        }
        if (memberView.birthday != null) {
            member.setBirthday(memberView.birthday);
        }

        membersDAO.update(member);
    }
}
