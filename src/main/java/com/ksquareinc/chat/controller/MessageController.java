package com.ksquareinc.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksquareinc.chat.model.Message;
import com.ksquareinc.chat.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Message message){
		Message newMessage = messageService.create(message);
		return ResponseEntity.ok().body("New message added: id = " + newMessage.getId() 
		+ ", conversation = " + newMessage.getConversation() + ", sender = " + newMessage.getSender() 
		+ ", creation date = " + newMessage.getCreationDate());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Message> findOne(@PathVariable("id") Long id) {
		Message message = messageService.findOne(id);
		return ResponseEntity.ok().body(message);
	}

	@GetMapping("/")
	public ResponseEntity<List<Message>> findAll() {
		List<Message> messages = messageService.findAll();
		return ResponseEntity.ok().body(messages);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Message message) {
		messageService.update(message);
		return ResponseEntity.ok().body("Message has been updated successfully id = " + message.getId() + ", text = " + message.getText());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		messageService.deleteById(id);
		return ResponseEntity.ok().body("Message has been deleted successfully");
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Message message) {
		messageService.delete(message);
		return ResponseEntity.ok().body("Message has been deleted successfully");
	}
}