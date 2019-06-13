package com.ksquareinc.chat.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "User")
@Table(name = "User")
@Transactional
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "active", nullable = false)
	private boolean active;

	@JsonIgnore
	@ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Conversation> conversations;
	
	@JsonIgnore
	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private List<Conversation> creations;
	
	public User() {
		
	}

	public User(String username, boolean active) {
		this.username = username;
		this.active = active;
	}
	
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public List<Conversation> getCreations() {
		return creations;
	}

	public void setCreations(List<Conversation> creations) {
		this.creations = creations;
	}
	
	@Override
	public String toString() {
		return "User{" + "id=" + id + "\n"
				+ ", username=" + username + ", sender='" + active + "}";
	}
}
