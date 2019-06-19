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

import com.ksquareinc.chat.model.Conversation;
import com.ksquareinc.chat.service.ConversationService;

@RestController
@RequestMapping("/api/conversation")
public class ConversationController {

	@Autowired
	private ConversationService conversationService;

	@PostMapping
	public ResponseEntity<Conversation> create(@RequestBody Conversation conversation) {
		Conversation newConversation = conversationService.create(conversation);
		return new ResponseEntity<Conversation>(newConversation, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Conversation> findOne(@PathVariable("id") Long id) {
		Conversation conversation = conversationService.findOne(id);
		return new ResponseEntity<Conversation>(conversation, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Conversation>> findAll() {
		List<Conversation> conversations = conversationService.findAll();
		return new ResponseEntity<List<Conversation>>(conversations, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Conversation> update(@RequestBody Conversation conversation) {
		conversationService.update(conversation);
		return new ResponseEntity<Conversation>(conversation, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		conversationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Conversation conversation) {
		conversationService.delete(conversation);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}