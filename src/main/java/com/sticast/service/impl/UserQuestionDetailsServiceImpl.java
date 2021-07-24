package com.sticast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.UserQuestionDetails;
import com.sticast.entity.User;
import com.sticast.repository.UserQuestionDetailsDao;
import com.sticast.service.UserQuestionDetailsService;

import java.util.Optional;

@Service
public class UserQuestionDetailsServiceImpl implements UserQuestionDetailsService {

    @Autowired
	private UserQuestionDetailsDao userQuestionDetailsDao;
    	
    @Override
	public Optional<UserQuestionDetails> findByUserAndQuestion(User user, Question question) {
		return userQuestionDetailsDao.findByUserAndQuestion(user, question);
	}

	@Override
	public void updateShareQuantity(UserQuestionDetails userQuestionDetails, Forecast forecast) {
			if(forecast.getAnswer().equals("yes"))
				userQuestionDetails.setYesShareQnt(userQuestionDetails.getYesShareQnt() + forecast.getQuantity());
			else
				userQuestionDetails.setNoShareQnt(userQuestionDetails.getNoShareQnt() + forecast.getQuantity());
			userQuestionDetailsDao.save(userQuestionDetails);
	}

	@Override
	public UserQuestionDetails findByUser_IdAndQuestion_Id(Integer id, Integer id1) {
		return userQuestionDetailsDao.findByUser_IdAndQuestion_Id(id, id1);
	}
}