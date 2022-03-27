package com.sport.sport.DAO;

import com.sport.sport.entities.Seat;

import java.util.List;

public interface SeatsDAO {
    void save(Seat object);
    void update(Seat object);
    void delete(Seat object);
    Seat getById(Long objectId);
    List<Seat> getAll();
}
