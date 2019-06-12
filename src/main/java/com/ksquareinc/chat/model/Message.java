package com.ksquareinc.chat.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity(name = "Message")
@Table(name = "Message")
@Transactional
public class Message implements Serializable {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id", referencedColumnName = "id", nullable = false)
	private Conversation conversation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
	private User sender;
	
	@Column(name = "creation_date", nullable = false)
	private LocalDateTime creationDate;
	
	public Message() {
		
	}
	
	public Message(Conversation conversation, User sender, LocalDateTime creationDate) {
		this.conversation = conversation;
		this.sender = sender;
		this.creationDate = creationDate;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Message{" + "id=" + id + "\n"
				+ ", conversation=" + conversation.getName() + ", sender='" + sender.getUsername() 
				+ ", message=" + conversation.getMessages() + ", creation date=" +creationDate+ "}";
	}
}