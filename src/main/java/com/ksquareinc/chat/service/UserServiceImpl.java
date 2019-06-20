package com.ksquareinc.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksquareinc.chat.dao.UserDao;
import com.ksquareinc.chat.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User create(User user) {
		return userDao.create(user);
	}

	public User findOne(long id) {
		return userDao.findOne(id);
	}
	
	public User findByName(String username) {
		return userDao.findByName(username);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User update(User user) {
		return userDao.update(user);
	}

	public void deleteById(long id) {
		userDao.deleteById(id);
	}

	public void delete(User user) {
		userDao.delete(user);
	}
}