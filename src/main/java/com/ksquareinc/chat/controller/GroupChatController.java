package com.ksquareinc.chat.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ksquareinc.chat.model.GroupChat;
import com.ksquareinc.chat.model.Message;
import com.ksquareinc.chat.model.OutputMessage;

@RestController
public class GroupChatController {
	
	@MessageMapping("/chat/{topic}")
	@SendTo("/topic/messages/{topic}")
	public OutputMessage send(@DestinationVariable("topic") String topic, Message message) {
		String time = new SimpleDateFormat("HH:mm").format(new Date());
	    return new OutputMessage(message.getFrom(), message.getText(), time, topic);
	}
	
	/*Get all group static.
	@GetMapping("/chat/topics")
	public ResponseEntity<List<GroupChat>> getAllGroupChat(){
		List<GroupChat> list = new ArrayList<GroupChat>();
		GroupChat gc1=new GroupChat("GroupChat n1", "Luis and Rodrigo");
		GroupChat gc2=new GroupChat("GroupChat n2", "Pedro and Daniels");
		list.add(gc1);
		list.add(gc2);
		
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping("/chat/topics/{topic}")
	public ResponseEntity<String> deleteGroupChat(@PathVariable("topic") String topic) {
		return ResponseEntity.ok().body("Deleted "+topic);
	}
	
	@PostMapping("/chat/topics")
	public ResponseEntity<String> addGroupChat(@RequestBody String topic){
		return ResponseEntity.ok().body("Added Group "+topic);
	}
	
	@PutMapping("")*/
}