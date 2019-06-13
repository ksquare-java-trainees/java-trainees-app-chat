package com.ksquareinc.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksquareinc.chat.dao.ConversationDao;
import com.ksquareinc.chat.model.Conversation;

@Service
@Transactional
public class ConversationServiceImpl implements ConversationService {

	@Autowired
	private ConversationDao conversationDao;
	
	@Override
	public Conversation create(Conversation conversation) {
		return conversationDao.create(conversation);
	}

	@Override
	public Conversation findOne(long id) {
		return conversationDao.findOne(id);
	}

	@Override
	public List<Conversation> findAll() {
		return conversationDao.findAll();
	}

	@Override
	public Conversation update(Conversation conversation) {
		return conversationDao.update(conversation);
	}

	@Override
	public void deleteById(long id) {
		conversationDao.deleteById(id);
	}

	@Override
	public void delete(Conversation conversation) {
		conversationDao.delete(conversation);
	}
}