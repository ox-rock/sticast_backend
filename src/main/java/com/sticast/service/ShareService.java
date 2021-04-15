package com.sticast.service;

import com.sticast.entity.Question;
import com.sticast.entity.Share;
import com.sticast.entity.User;

// TODO Remove trigger from DB 

public interface ShareService {

	Share findByUserAndQuestion(User user, Question question);
}