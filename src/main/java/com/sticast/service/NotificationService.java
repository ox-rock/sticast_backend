package com.sticast.service;

import java.util.List;

import com.sticast.entity.Notification;
import com.sticast.entity.Question;
import com.sticast.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
	public void sendCommentNotification(User userSender, Question question);
	public List<Notification> findNotificationByUsername(User user);
}
