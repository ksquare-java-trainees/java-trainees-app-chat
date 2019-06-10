package com.ksquareinc.chat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ksquareinc.chat.model.Message;
import com.ksquareinc.chat.model.OutputMessage;

@Controller
public class GroupChatController {
	
	@MessageMapping("/chat/{topic}")
	@SendTo("/topic/messages/{topic}")
	public OutputMessage send(@DestinationVariable("topic") String topic, Message message) {
		String time = new SimpleDateFormat("HH:mm").format(new Date());
	    return new OutputMessage(message.getFrom(), message.getText(), time, topic);
	}
}