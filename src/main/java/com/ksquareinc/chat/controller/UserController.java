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

import com.ksquareinc.chat.model.User;
import com.ksquareinc.chat.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "User Management System")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Save an user")
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		User newUser = userService.create(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get an user by the id")
	@GetMapping("/{id}")
	public ResponseEntity<User> findOne(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "View a list of all available users")
	@GetMapping("/")
	public ResponseEntity<List<User>> findAll() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@ApiOperation(value = "Update an user")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody User user) {
		userService.update(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete an user by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete an user")
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody User user) {
		userService.delete(user);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}