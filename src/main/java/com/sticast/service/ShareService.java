package com.sticast.service;

import com.sticast.entity.Question;
import com.sticast.entity.Share;
import com.sticast.entity.User;

public interface ShareService {

	Share findByUserAndQuestion(User user, Question question);
}