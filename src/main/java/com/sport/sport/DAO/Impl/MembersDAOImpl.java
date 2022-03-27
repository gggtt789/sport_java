package com.sport.sport.DAO.Impl;

import com.sport.sport.DAO.MembersDAO;
import com.sport.sport.entities.Member;
import com.sport.sport.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MembersDAOImpl implements MembersDAO {
    @Override
    public Member getById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Member object = session.get(Member.class, id);
        session.close();
        return object;
    }

    @Override
    public List<Member> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Member> query = session.createQuery("FROM Member ", Member.class);
        List<Member> members = query.list();
        session.getTransaction().commit();
        session.close();
        return members;
    }

    @Override
    public void save(Member object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Member object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.close();
    }
}
