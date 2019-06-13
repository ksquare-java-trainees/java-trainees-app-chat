package com.ksquareinc.chat.model;

public class OutputMessage {

	private String from;
	private String text;
	private String time;
	private String channel;

	public OutputMessage(String from, String text, String time) {
		this.from = from;
		this.text = text;
		this.time = time;
	}

	public OutputMessage(String from, String text, String time, String channel) {
		this(from, text, time);
		this.channel = channel;
	}

	public OutputMessage() {

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

	public void setText(String message) {
		this.text = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}