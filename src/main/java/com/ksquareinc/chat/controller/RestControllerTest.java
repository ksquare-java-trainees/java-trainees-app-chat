package com.ksquareinc.chat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerTest {

	@GetMapping("/api/user")
	public String requestGet() {
		return "WORKING";
	}
	
}
