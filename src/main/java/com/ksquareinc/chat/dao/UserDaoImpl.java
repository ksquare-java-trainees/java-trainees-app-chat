package com.ksquareinc.chat.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksquareinc.chat.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User create(User user) {
		sessionFactory.getCurrentSession().save(user);
		return user;
	}

	@Override
	public User findOne(long id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        Query<User> query = session.createQuery(cq);
        return query.getResultList();
	}

	@Override
	public User update(User user) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.merge(user);
	}

	@Override
	public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
	}

	@Override
	public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = findOne(id);
        session.delete(user);
	}
}