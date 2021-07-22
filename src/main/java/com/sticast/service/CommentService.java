package com.sticast.service;

import com.sticast.entity.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
	public void save(Comment comment);
}
