package com.sticast.service;

import java.util.List;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {
	List<Question> findAll();
	Question findById(Integer questionID);
	Double calculateShareValue(Question question);
	void updateShareQuantity(Forecast forecast);
	void updateFollow(String requestType, Question question, User user);
	void closeQuestion(Question question, String winnerType);
}