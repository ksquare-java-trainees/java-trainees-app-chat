package com.ksquareinc.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<Message> create(@RequestBody Message message){
		Message newMessage = messageService.create(message);
		return new ResponseEntity<Message>(newMessage, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Message> findOne(@PathVariable("id") Long id) {
		Message message = messageService.findOne(id);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Message>> findAll() {
		List<Message> messages = messageService.findAll();
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Message> update(@RequestBody Message message) {
		messageService.update(message);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		messageService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Message message) {
		messageService.delete(message);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}