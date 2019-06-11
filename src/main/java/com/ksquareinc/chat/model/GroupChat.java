package com.ksquareinc.chat.model;

//Practice
public class GroupChat {
	
	private String name;
	private String participantes;
	
	public GroupChat(String name, String participantes) {
		this.name = name;
		this.participantes = participantes;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParticipantes() {
		return participantes;
	}
	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}
	
	
}
