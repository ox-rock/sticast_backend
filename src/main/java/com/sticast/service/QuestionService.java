package com.sticast.service;

import java.util.List;
import java.util.Optional;

import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.User;

public interface QuestionService {

	List<Question> findAll();
	Optional<Question> findByIdAndFollowed_Id(Integer questionid, Integer id);
	Question findById(Integer questionID);
	Double calculateShareValue(Question question);
	void updateShareQuantity(Forecast forecast);
	void updateFollow(String requestType, Question question, User user);
	void closeQuestion(Question question, String winnerType);
	
}