package com.sport.sport.DAO.Impl;

import com.sport.sport.DAO.MemberTeamsDAO;
import com.sport.sport.entities.MemberTeam;
import com.sport.sport.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MemberTeamsDAOImpl implements MemberTeamsDAO {
    @Override
    public MemberTeam getById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        MemberTeam object = session.get(MemberTeam.class, id);
        session.close();
        return object;
    }

    @Override
    public List<MemberTeam> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<MemberTeam> query = session.createQuery("FROM MemberTeam ", MemberTeam.class);
        List<MemberTeam> tickets = query.list();
        session.getTransaction().commit();
        session.close();
        return tickets;
    }

    @Override
    public void save(MemberTeam object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(MemberTeam object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(MemberTeam object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }
}
