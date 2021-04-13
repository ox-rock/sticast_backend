package com.sticast.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.User;
import com.sticast.service.ForecastService;
import com.sticast.service.QuestionService;
import com.sticast.service.UserService;

@Controller
public class ForecastController {

	@Autowired
    ForecastService forecastService;
	
	@Autowired 
	QuestionService questionService;
	
	@Autowired 
	UserService userService;

	@PostMapping(value = "/question/{questionID}")
	public String makeForecast(@PathVariable Integer questionID, HttpServletRequest request, @ModelAttribute("forecast") Forecast forecast) {
		
		HttpSession session = request.getSession();
		User tmpUser = (User) session.getAttribute("user");	
    	Question question = questionService.findById(questionID);
		User user = userService.findById(tmpUser.getId());
		
		forecast.setQuestion(question);
		forecast.setUser(user);
		forecastService.makeForecast(forecast);
		questionService.updateShareQuantity(forecast);
		

		user.setBudget(user.getBudget() - forecast.getPayout());
		userService.save(user);
		
		session.setAttribute("user", user);
		return "redirect:/question/"+questionID; 	   	
    }
}