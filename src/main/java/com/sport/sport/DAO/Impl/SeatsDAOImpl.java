package com.sport.sport.DAO.Impl;

import com.sport.sport.DAO.SeatsDAO;
import com.sport.sport.entities.Seat;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.sport.sport.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class SeatsDAOImpl implements SeatsDAO {
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
        session.getTransaction().commit();
        session.close();
    }
}
