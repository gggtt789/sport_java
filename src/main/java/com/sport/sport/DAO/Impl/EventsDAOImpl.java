package com.sport.sport.DAO.Impl;

import com.sport.sport.DAO.EventsDAO;
import com.sport.sport.entities.Event;

import com.sport.sport.utils.HibernateSessionFactoryUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EventsDAOImpl implements EventsDAO {
    @Override
    public Event getById(Long objectId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Event object = session.get(Event.class, objectId);
        session.close();
        return object;
    }

    @Override
    public List<Event> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Event> query = session.createQuery("FROM Event", Event.class);
        List<Event> events = query.list();
        session.getTransaction().commit();
        session.close();
        return events;
    }

    @Override
    public void save(Event object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.evict(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Event object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Event object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }
}
