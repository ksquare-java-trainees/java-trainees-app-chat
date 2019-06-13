package com.ksquareinc.chat.model;

import java.time.LocalDateTime;

public class MessagePr {
	
	private String from;
	private String text;
	private LocalDateTime date;
	private String topic;
	
	public MessagePr() {
		
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
}
