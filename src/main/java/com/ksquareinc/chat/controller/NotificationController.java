package com.ksquareinc.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ksquareinc.chat.model.Notification;

@RestController
public class NotificationController {

	@Autowired
	private Notification notification;
	
	
}
