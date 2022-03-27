package com.sport.sport.DAO;

import com.sport.sport.entities.Team;

import java.util.List;

public interface TeamsDAO {
    Team getById(Long objectId);
    List<Team> getAll();
    void save(Team object);
    void update(Team object);
}
