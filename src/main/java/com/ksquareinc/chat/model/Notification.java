package com.ksquareinc.chat.model;

public class Notification {
	
	private String information;
	
	public Notification() {
		
	}
	
	public Notification(String information) {
		this.setInformation(information);
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}