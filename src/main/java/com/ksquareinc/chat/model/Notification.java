package com.ksquareinc.chat.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification implements Serializable {

	private String id;
	private User creator;
	private String subject;
	private boolean isAllDay;
	private String dateBegin;
	private List<User> users;
	private LocalDateTime dateTime;
	private String details;

	public Notification() {

	}

	public Notification(String subject, User creator, List<User> users, String dateBegin, LocalDateTime dateTime,
			String details) {
		this.subject = subject;
		this.creator = creator;
		this.users = users;
		this.dateBegin = dateBegin;
		this.dateTime = dateTime;
		this.details = details;
	}

	public Message toMessage(Notification notification) {
		Message message = new Message();
		message.setConversation(new Conversation(notification.getSubject(), notification.creator,
				notification.getDateTime(), Type.PRIVATE_CHAT));
		message.setSender(notification.getCreator());
		message.setCreationDate(notification.getDateTime());
		message.setText(notification.getDetails() + notification.getUsers().toString() + notification.getDateBegin());
		return message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public boolean isAllDay() {
		return isAllDay;
	}

	public void setAllDay(boolean isAllDay) {
		this.isAllDay = isAllDay;
	}

	public String getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}

	public String getId() {
		return id;
	}
}