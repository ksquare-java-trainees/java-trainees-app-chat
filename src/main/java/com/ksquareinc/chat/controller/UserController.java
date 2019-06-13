package com.ksquareinc.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
		return ResponseEntity.ok().body("New user added: id = " + newUser.getId() + ", username = " + newUser.getUsername());
	}
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