package com.ksquareinc.chat.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Notification implements Serializable {

	private String id;
	private User creator;
	private String subject;
	private LocalDateTime dateBegin;
	private LocalDateTime dateEnd;
	private List<User> guests;
	private String description;
	private Type type = Type.NOTIFICATION;

	public Notification() {

	}
	
	public Notification(User creator, String subject, LocalDateTime dateBegin, LocalDateTime dateEnd, List<User> guests, String description) {
		this.creator = creator;
		this.subject = subject;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.guests = guests;		
		this.description = description;
	}
	
	public Message toMessage() {
		Message message = new Message();
		message.setSender(this.creator);
		message.setText(createText());
		//message.setConversation(new Conversation(notification.getSubject(), notification.creator,
			//	notification.getDateTime(), Type.PRIVATE_CHAT));
		//message.setCreationDate(notification.getDateTime());
		return message;
	}
	
	private String createText() {
		StringBuilder text = new StringBuilder(500);
		text.append("Subject: " 	+ this.subject 		+ " - ");
		text.append("More Info: " 	+ this.description 	+ " - ");
		text.append("Start Date: " 	+ this.dateBegin 	+ " - ");
		if(this.dateEnd == null) {
			text.append("All Day");
		} else {
			text.append("End Date: " + this.dateEnd);
		}
		return text.toString();
	}
	
}
