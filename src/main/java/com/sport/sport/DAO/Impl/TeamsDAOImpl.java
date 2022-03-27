package com.sport.sport.DAO.Impl;

import com.sport.sport.DAO.TeamsDAO;
import com.sport.sport.entities.Team;

import com.sport.sport.utils.HibernateSessionFactoryUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TeamsDAOImpl implements TeamsDAO {
    @Override
    public Team getById(Long objectId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Team object = session.get(Team.class, objectId);
        session.close();
        return object;
    }

    @Override
    public List<Team> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Team> query = session.createQuery("FROM Team ", Team.class);
        List<Team> tickets = query.list();
        session.getTransaction().commit();
        session.close();
        return tickets;
    }

    @Override
    public void save(Team object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Team object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.close();
    }
}
