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

import com.ksquareinc.chat.model.Message;

@Repository
public class MessageDaoImpl implements MessageDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Message create(Message message) {
		sessionFactory.getCurrentSession().save(message);
		return message;
	}

	public Message findOne(long id) {
		return sessionFactory.getCurrentSession().get(Message.class, id);
	}

	public List<Message> findAll() {
		Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Message> cq = cb.createQuery(Message.class);
        Root<Message> root = cq.from(Message.class);
        cq.select(root);
        Query<Message> query = session.createQuery(cq);
        return query.getResultList();
	}

	public Message update(Message message) {
		Session session = sessionFactory.getCurrentSession();
		return (Message)session.merge(message);
	}

	public void delete(Message message) {
		Session session = sessionFactory.getCurrentSession();
        session.delete(message);
	}

	public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Message message = findOne(id);
        session.delete(message);
	}
}