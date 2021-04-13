package com.sticast.service;

import java.util.List;
import java.util.Optional;

import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.User;

public interface QuestionService {

	public List<Question> findAll();
	public Question findById(Integer questionID);
	public Double calculateShareValue(Question question);
//	public void closeQuestion(Integer questionID);
	void updateShareQuantity(Forecast forecast);
	public void updateFollow(String requestType, Question question, User user);
	public Optional<Question> findByIdAndFollowed_Id(Integer questionid, Integer id);
	public void closeQuestion(Question question, String winnerType);
	
}
