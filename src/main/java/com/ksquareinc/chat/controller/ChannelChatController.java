package com.ksquareinc.chat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ksquareinc.chat.model.MessagePr;
import com.ksquareinc.chat.model.OutputMessage;

@Controller
public class ChannelChatController {
	
	@MessageMapping("/chat/{channel}")
	@SendTo("/topic/messages/{channel}")
	public OutputMessage send(@DestinationVariable("channel") String channel, MessagePr messagePr) {
		String time = new SimpleDateFormat("HH:mm").format(new Date());
	    return new OutputMessage(messagePr.getFrom(), messagePr.getText(), time, channel);
	}
}