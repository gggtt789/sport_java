package com.sport.sport.DAO.Impl;

import com.sport.sport.DAO.SeatsDAO;
import com.sport.sport.entities.Seat;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.sport.sport.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class SeatsDAOImpl implements SeatsDAO {

    @Override
    public Seat getById(Long objectId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Seat object = session.get(Seat.class, objectId);
        session.close();
        return object;
    }

    @Override
    public List<Seat> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Seat> query = session.createQuery("FROM Seat ", Seat.class);
        List<Seat> tickets = query.list();
        session.getTransaction().commit();
        session.close();
        return tickets;
    }

    @Override
    public void save(Seat object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.evict(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Seat object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(object);
        session.evict(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Seat object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }
}
