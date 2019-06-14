package com.ksquareinc.chat.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Conversation")
@Table(name = "Conversation")
@Transactional
public class Conversation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name",unique = true, nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator", referencedColumnName = "id", nullable = false)
	private User creator;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_conversation", joinColumns = @JoinColumn(name = "conversation_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
	private List<Message> messages;

	@Column(name = "creation_date", nullable = false)
	private LocalDateTime creationDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private Type type;
	
	public Conversation() {
		
	}
	
	public Conversation(String name, User creator, LocalDateTime creationDate, Type type) {
		this.name = name;
		this.creator = creator;
		this.creationDate = creationDate;
		this.type = type;
	}
	
	public Long getId() {
		return id;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Conversation{" + "id=" + id + "\n"
				+ ", name=" + name + ", creator='" + creator.getUsername() + "\n"
				+ ", creationDate=" + creationDate + ", type=" + type + "}";
	}
}