package com.sport.sport.DAO;

import com.sport.sport.entities.MemberTeam;

import java.util.List;

public interface MemberTeamsDAO {
    void save(MemberTeam object);
    void update(MemberTeam object);
    void delete(MemberTeam object);
    MemberTeam getById(Long objectId);
    List<MemberTeam> getAll();
}
