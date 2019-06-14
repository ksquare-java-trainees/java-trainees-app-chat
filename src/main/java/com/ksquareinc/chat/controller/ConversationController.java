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

import com.ksquareinc.chat.model.Conversation;
import com.ksquareinc.chat.service.ConversationService;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

	@Autowired
	private ConversationService conversationService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Conversation conversation) {
		Conversation newConversation = conversationService.create(conversation);
		return ResponseEntity.ok().body("New Conversation added: id = " + newConversation.getId() + ", name = " + newConversation.getName());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Conversation> findOne(@PathVariable("id") Long id) {
		Conversation conversation = conversationService.findOne(id);
		return ResponseEntity.ok().body(conversation);
	}

	@GetMapping("/")
	public ResponseEntity<List<Conversation>> findAll() {
		List<Conversation> conversations = conversationService.findAll();
		return ResponseEntity.ok().body(conversations);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Conversation conversation) {
		conversationService.update(conversation);
		return ResponseEntity.ok().body("Conversation has been updated successfully id = " + conversation.getId() + ", name = " + conversation.getName());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		conversationService.deleteById(id);
		return ResponseEntity.ok().body("Conversation has been deleted successfully");
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Conversation conversation) {
		conversationService.delete(conversation);
		return ResponseEntity.ok().body("Conversation has been deleted successfully");
	}
}