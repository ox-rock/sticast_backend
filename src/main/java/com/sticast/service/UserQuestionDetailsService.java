package com.sticast.service;

import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.UserQuestionDetails;
import com.sticast.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO Remove trigger from DB 
@Service
public interface UserQuestionDetailsService {
	Optional<UserQuestionDetails> findByUserAndQuestion(User user, Question question);
	void updateShareQuantity(UserQuestionDetails userQuestionDetails, Forecast forecast);
    UserQuestionDetails findByUser_IdAndQuestion_Id(Integer id, Integer id1);
}