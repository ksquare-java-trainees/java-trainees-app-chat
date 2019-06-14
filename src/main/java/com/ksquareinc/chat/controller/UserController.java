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

import com.ksquareinc.chat.model.User;
import com.ksquareinc.chat.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {
		User newUser = userService.create(user);
		return ResponseEntity.ok()
				.body("New user added: id = " + newUser.getId() + ",\n username = " + newUser.getUsername());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findOne(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> findAll() {
		List<User> users = userService.findAll();
		return ResponseEntity.ok().body(users);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody User user) {
		userService.update(user);
		return ResponseEntity.ok().body("User has been updated successfully id = " + user.getId() + ", username = " + user.getUsername());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return ResponseEntity.ok().body("User has been deleted successfully");
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody User user) {
		userService.delete(user);
		return ResponseEntity.ok().body("User has been deleted successfully");
	}
}