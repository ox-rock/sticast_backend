package com.sticast.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticast.entity.Notification;
import com.sticast.entity.Question;
import com.sticast.entity.User;
import com.sticast.repository.NotificationDao;
import com.sticast.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {


	@Autowired 
	NotificationDao notificationRepository;
	
	@Override
	public void sendCommentNotification(User userSender, Question question) {	
		
		Notification commentNotification = new Notification();
		commentNotification.setType("comment");
		commentNotification.setQuestion(question);
		commentNotification.setSender(userSender);
		
		Set<User> followers_h = question.getFollowed();
		List<User> followers = new ArrayList<>(followers_h);
	
		
		for (int i = 0; i < followers.size(); i++) {
		if(followers.get(i).getUserSettings().getCommentNotification() == 1) {
			commentNotification.setReciever(followers.get(i));
			notificationRepository.save(commentNotification);			
		}
		}
		
	}

	@Override
	public List<Notification> findNotificationByUsername(User user) {
		
		return notificationRepository.findByReciever(user);
	}
}