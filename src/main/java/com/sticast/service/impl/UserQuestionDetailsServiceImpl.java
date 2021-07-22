package com.sticast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.UserQuestionDetails;
import com.sticast.entity.User;
import com.sticast.repository.UserQuestionDetailsDao;
import com.sticast.service.UserQuestionDetailsService;

@Service
public class UserQuestionDetailsServiceImpl implements UserQuestionDetailsService {

    @Autowired
	private UserQuestionDetailsDao shareRepository;
    	
    @Override
	public UserQuestionDetails findByUserAndQuestion(User user, Question question) {
		return shareRepository.findByUserAndQuestion(user, question).orElse(new UserQuestionDetails(null));
	}

	@Override
	public void updateShareQuantity(User user, Question question, Forecast forecast) {
		UserQuestionDetails share = new UserQuestionDetails();
		share = shareRepository.findByUserAndQuestion(user, question).orElse(new UserQuestionDetails(null));
		if(share != null) {
			if(forecast.getAnswer().equals("yes")) 
			    share.setYesShareQnt(share.getYesShareQnt()+forecast.getQuantity());
			else 
				share.setNoShareQnt(share.getNoShareQnt()+forecast.getQuantity());
		}
	}

	@Override
	public UserQuestionDetails findByUser_IdAndQuestion_Id(Integer id, Integer id1) {
		return shareRepository.findByUser_IdAndQuestion_Id(id, id1);
	}
}