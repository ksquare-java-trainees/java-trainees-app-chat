package com.ksquareinc.chat.service;

import com.ksquareinc.chat.model.Notification;

public interface NotificationService {
	
	Notification doNotify(Notification notification);
}