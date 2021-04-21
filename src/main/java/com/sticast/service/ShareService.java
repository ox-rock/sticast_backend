package com.sticast.service;

import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.Share;
import com.sticast.entity.User;

// TODO Remove trigger from DB 

public interface ShareService {

	Share findByUserAndQuestion(User user, Question question);
	void updateShareQuantity(User user, Question question, Forecast forecast);
}