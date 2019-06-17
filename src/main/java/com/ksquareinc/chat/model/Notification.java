package com.ksquareinc.chat.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Notification implements Serializable{

	private String name;
	private List<User> users;
	private LocalDateTime dateTime;
	private String details;
	
	public Notification() {
		
	}
	
	public Notification(String name, List<User> users, LocalDateTime dateTime, String details) {
		this.name = name;
		this.users = users;
		this.dateTime = dateTime;
		this.details = details;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}