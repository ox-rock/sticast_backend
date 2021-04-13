package com.sticast.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Notification;
import com.sticast.entity.User;

@Repository("NotificationDao")
public interface NotificationDao extends JpaRepository<Notification, Integer> {

	List<Notification> findByReciever(User user);
}
