package com.sticast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.repository.ForecastDao;
import com.sticast.repository.UserDao;
import com.sticast.service.ForecastService;
import com.sticast.service.QuestionService;

@Service
public class ForecastServiceImpl implements ForecastService {

	@Autowired
	QuestionService questionService;

	@Autowired 
	ForecastDao forecastRepository;
	
	@Autowired 
	UserDao userRepository;

	@Override
	public Double calculatePayout(Forecast forecast) { 				
		final double B = 100.0; //Maximum possible amount of money the market maker can lose	
		
		if(forecast.getAnswer().equals("yes")) {
		    return B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity() + forecast.getQuantity())/B) + Math.exp(forecast.getQuestion().getNoShareQuantity()/B))) - 
			       B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity()/B)) + Math.exp(forecast.getQuestion().getNoShareQuantity()/B)));
		}
		else {
			return B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity())/B) + Math.exp((forecast.getQuestion().getNoShareQuantity() + forecast.getQuantity())/B))) - 
				   B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity())/B) + Math.exp(forecast.getQuestion().getNoShareQuantity()/B)));
	    }
	}

	public void makeForecast(Forecast forecast) {
		Question question = questionService.findById(forecast.getQuestion().getId());
		forecast.setQuestion(question);
		
		double payout = calculatePayout(forecast);
		forecast.setPayout(payout);
		forecastRepository.save(forecast);	
		
	}

	@Override
	public java.util.List<Forecast> findByUsername_Id(com.sticast.entity.User user) {
	
		return forecastRepository.findByUser(user);
	}


}
