package com.sticast.service;

import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.UserQuestionDetails;
import com.sticast.entity.User;
import org.springframework.stereotype.Service;

// TODO Remove trigger from DB 
@Service
public interface UserQuestionDetailsService {

	UserQuestionDetails findByUserAndQuestion(User user, Question question);
	void updateShareQuantity(User user, Question question, Forecast forecast);
    UserQuestionDetails findByUser_IdAndQuestion_Id(Integer id, Integer id1);
}