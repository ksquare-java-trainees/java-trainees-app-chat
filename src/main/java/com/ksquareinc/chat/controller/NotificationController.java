package com.ksquareinc.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksquareinc.chat.model.Notification;
import com.ksquareinc.chat.service.NotificationService;

@RestController
@RequestMapping("/notify")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping
	public ResponseEntity<?> doNotify(@RequestBody Notification notification) {
		Notification newNotification = notificationService.doNotify(notification);
		return ResponseEntity.ok().body("New notification : " + newNotification.getName() + newNotification.getUsers()
				+ newNotification.getDateTime() + newNotification.getDetails());
	}
}