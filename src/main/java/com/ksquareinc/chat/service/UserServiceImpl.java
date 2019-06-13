package com.ksquareinc.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksquareinc.chat.dao.UserDao;
import com.ksquareinc.chat.model.User;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User create(User user) {
		return userDao.create(user);
	}

	@Override
	public User findOne(long id) {
		return userDao.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public void deleteById(long id) {
		userDao.deleteById(id);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}
}