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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sticast.entity.Forecast;
import com.sticast.entity.Notification;
import com.sticast.entity.User;
import com.sticast.entity.UserSettings;
import com.sticast.service.ForecastService;
import com.sticast.service.NotificationService;
import com.sticast.service.QuestionService;
import com.sticast.service.UserService;

@Controller
public class UserController {

	@Autowired
    ForecastService forecastService;
	
	@Autowired 
	QuestionService questionService;
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	NotificationService notificationService;
	

	/******* Get user profile *******/
	
	@GetMapping(value = "/profile")
	public String showProfile(Model model, HttpServletRequest request) {	     
		HttpSession session = request.getSession(false);
		User tmpUser = (User) session.getAttribute("user");
		   	
		User user = userService.findById(tmpUser.getId());
		model.addAttribute("user", user);
		model.addAttribute("userSettings", user.getUserSettings());
		  
		return "profile";
	}
	
	/******* Edit user profile *******/
	
	@PostMapping(value = "/profile")
	public void editProfile(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam String userName, @RequestParam String email) throws ServletException, IOException{
	 	HttpSession session = request.getSession(false);    
	 	PrintWriter out = response.getWriter();
	 	User tmpUser = (User) session.getAttribute("user");
	 		   	
		User user = userService.findById(tmpUser.getId());
		user.setUserName(userName);
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
    	
    	UserSettings userSettings = new UserSettings();
    	userSettings.setId(tmpUser.getId());
    	
		if (closed) 
			userSettings.setClosedQuestionNotification(1);
		else userSettings.setClosedQuestionNotification(0);
		
		if (comment) 
			userSettings.setCommentNotification(1); 
		else userSettings.setCommentNotification(0); 
		
		User user = userService.findById(tmpUser.getId());
		user.setUserSettings(userSettings);
		userService.save(user);
	}
}