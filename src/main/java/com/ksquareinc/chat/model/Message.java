package com.ksquareinc.chat.model;

import java.util.Calendar;

public class Message {
	 
    private String from;
    private String to;
    private String message;
    private Calendar created = Calendar.getInstance();
    
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Calendar getCreated() {
		return created;
	}
	public void setCreated(Calendar created) {
		this.created = created;
	}
}
