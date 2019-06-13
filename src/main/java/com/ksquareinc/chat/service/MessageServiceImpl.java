package com.ksquareinc.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksquareinc.chat.dao.MessageDao;
import com.ksquareinc.chat.model.Message;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	
	@Override
	public Message create(Message message) {
		return messageDao.create(message);
	}

	@Override
	public Message findOne(long id) {
		return messageDao.findOne(id);
	}

	@Override
	public List<Message> findAll() {
		return messageDao.findAll();
	}

	@Override
	public Message update(Message message) {
		return messageDao.update(message);
	}

	@Override
	public void deleteById(long id) {
		messageDao.deleteById(id);
	}

	@Override
	public void delete(Message message) {
		messageDao.delete(message);
	}
}