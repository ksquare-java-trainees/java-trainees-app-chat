package com.ksquareinc.chat.service;

import com.ksquareinc.chat.model.User;

public interface UserService extends GenericService<User>{

	User findByName(String username);
	
}
