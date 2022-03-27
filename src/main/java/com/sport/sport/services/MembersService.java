package com.sport.sport.services;

import com.sport.sport.views.MemberView;

import java.util.List;

public interface MembersService {
    List<MemberView> getAll();
    MemberView getMember(Long id);
    void deleteMember(Long id);
    void addMember(MemberView memberView);
    void updateMember(Long id, MemberView memberView);
}
