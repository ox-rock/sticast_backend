package com.sticast.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sticast.entity.Forecast;
import com.sticast.entity.Notification;
import com.sticast.entity.User;
import com.sticast.entity.UserDetails;
import com.sticast.service.ForecastService;
import com.sticast.service.NotificationService;
import com.sticast.service.QuestionService;
import com.sticast.service.UserService;

@RestController
public class UserController {
    ForecastService forecastService;
	QuestionService questionService;
	UserService userService;
	NotificationService notificationService;

	@Autowired
	public UserController(ForecastService forecastService,
						  QuestionService questionService,
						  UserService userService,
						  NotificationService notificationService) {
		this.forecastService = forecastService;
		this.questionService = questionService;
		this.userService = userService;
		this.notificationService = notificationService;
	}

	/******* Get user profile *******/
	
	@GetMapping(value = "/profile")
	public String showProfile(Model model, HttpServletRequest request) {	     
		HttpSession session = request.getSession(false);
		User tmpUser = (User) session.getAttribute("user");
		   	
		User user = userService.findById(tmpUser.getId());
		model.addAttribute("user", user);
		model.addAttribute("userSettings", user.getUserDetails());
		  
		return "profile";
	}
	
	/******* Edit user profile *******/
	
	@PostMapping(value = "/profile")
	public void editProfile(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam String username, @RequestParam String email) throws ServletException, IOException{
	 	HttpSession session = request.getSession(false);    
	 	PrintWriter out = response.getWriter();
	 	User tmpUser = (User) session.getAttribute("user");
	 		   	
		User user = userService.findById(tmpUser.getId());
		user.setUsername(username);
		user.setEmail(email);
	 	try { 
	 		userService.save(user);
	 	} 
	 	catch(DataIntegrityViolationException ex) {
		 	model.addAttribute("user", user);
		 	model.addAttribute("editProfileCheck", "error");
		 	out.write("error");  	  
	 	}	  
	 	out.write("ok");
	 	model.addAttribute("user", user);	
		model.addAttribute("editProfileCheck", "success");
	}
	
	/******* Shows user's forecasts *******/
	
	@GetMapping(value = "/profile/forecasts")
	public String showForecastsList(HttpServletRequest request) {
		  
	    HttpSession session = request.getSession(false);			
	    User tmpUser = (User) session.getAttribute("user");  	
	    User user = userService.findById(tmpUser.getId());
			
		List<Forecast> forecastsList = forecastService.findByUsername_Id(user);
		request.setAttribute("forecast", forecastsList);
			
		return "forecasts"; 
	}
	
	@PostMapping(value = "/profile/enableDisable2fa")
	public String enableDisable2fa(Model model, HttpServletRequest request) {	     
	
		return "profile";
	}
	
	/******* Shows user's notifications *******/
	
	@GetMapping(value = "/profile/notifications")
	  public String showNotifications(Model model, HttpServletRequest request) {	     
	       HttpSession session = request.getSession(false);
	       User tmpUser = (User) session.getAttribute("user");
	   	
		   User user = userService.findById(tmpUser.getId());
		  
		   List<Notification> notificationsList = notificationService.findNotificationByUsername(user);
		   
		   model.addAttribute("notificationsList", notificationsList);	
		   
		   return "notifications";
	}
	
	/******* Updates user's notification preferences *******/
	
	@PostMapping(value = "/profile/updateNotificationPreferences")
	@ResponseBody
	public void updateNotificationPreferences(HttpServletRequest request, @RequestParam(defaultValue = "false") boolean closed, @RequestParam(defaultValue = "false") boolean comment) {	     
		HttpSession session = request.getSession(false);	
    	User tmpUser = (User) session.getAttribute("user");
    	
    	UserDetails userDetails = new UserDetails();
    	userDetails.setId(tmpUser.getId());
    	
		if (closed) 
			userDetails.setClosedQuestionNotification(true);
		else userDetails.setClosedQuestionNotification(false);
		
		if (comment) 
			userDetails.setCommentNotification(true);
		else userDetails.setCommentNotification(false);
		
		User user = userService.findById(tmpUser.getId());
		user.setUserDetails(userDetails);
		userService.save(user);
	}
}