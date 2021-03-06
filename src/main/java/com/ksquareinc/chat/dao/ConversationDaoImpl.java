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

import com.ksquareinc.chat.model.Conversation;

@Repository
public class ConversationDaoImpl implements ConversationDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Conversation create(Conversation conversation) {
		sessionFactory.getCurrentSession().save(conversation);
		return conversation;
	}

	public Conversation findOne(long id) {
		return sessionFactory.getCurrentSession().get(Conversation.class, id);
	}

	public List<Conversation> findAll() {
		Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Conversation> cq = cb.createQuery(Conversation.class);
        Root<Conversation> root = cq.from(Conversation.class);
        cq.select(root);
        Query<Conversation> query = session.createQuery(cq);
        return query.getResultList();
	}

	public Conversation update(Conversation conversation) {
		Session session = sessionFactory.getCurrentSession();
		return (Conversation)session.merge(conversation);
	}

	public void delete(Conversation conversation) {
		Session session = sessionFactory.getCurrentSession();
        session.delete(conversation);
	}

	public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Conversation conversation = findOne(id);
        session.delete(conversation);
	}
}
