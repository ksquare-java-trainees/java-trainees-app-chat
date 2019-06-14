package com.ksquareinc.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksquareinc.chat.dao.MessageDao;
import com.ksquareinc.chat.model.Message;

@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	
	public Message create(Message message) {
		return messageDao.create(message);
	}

	public Message findOne(long id) {
		return messageDao.findOne(id);
	}

	public List<Message> findAll() {
		return messageDao.findAll();
	}

	public Message update(Message message) {
		return messageDao.update(message);
	}

	public void deleteById(long id) {
		messageDao.deleteById(id);
	}

	public void delete(Message message) {
		messageDao.delete(message);
	}
}