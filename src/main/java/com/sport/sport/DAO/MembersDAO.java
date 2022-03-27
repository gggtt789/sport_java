package com.sport.sport.DAO;

import com.sport.sport.entities.Member;

import java.util.List;

public interface MembersDAO {
    void save(Member object);
    void update(Member object);
    Member getById(Long objectId);
    List<Member> getAll();
}
