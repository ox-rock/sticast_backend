package com.sticast.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.sticast.entity.Category;
import com.sticast.entity.Comment;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.Share;
import com.sticast.entity.User;
import com.sticast.service.CategoryService;
import com.sticast.service.CommentService;
import com.sticast.service.NotificationService;
import com.sticast.service.QuestionService;
import com.sticast.service.ShareService;
import com.sticast.service.UserService;

@Controller
@SessionAttributes("user")
public class QuestionController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ShareService shareService;

	/******* Shows all questions *******/
	// TODO Implement search functionality: by name, date and forecasts number with pagination
	
	@GetMapping(value = "/questions")
	public String showAllQuestions(Model model, HttpServletRequest request) {
	
		List<Question> questionsList = questionService.findAll();
		List<Category> categoriesList = categoryService.findAll();
	  	
		model.addAttribute("categoriesList", categoriesList);
		model.addAttribute("questionsList", questionsList);
		model.addAttribute("category", "all");

		return "questionslist";
    }
	
	// TODO Implement multiple question category support
	/******* Shows all questions of a given category  *******/
	
	@GetMapping(value = "/questions/{category}")
	public String showQuestionsByCategory(Model model, @PathVariable String category, HttpServletRequest request) {
	
		List<Question> questionsList = categoryService.findByName(category).getQuestions();
		List<Category> categoriesList = categoryService.findAll();

		model.addAttribute("categoriesList", categoriesList);
		model.addAttribute("questionsList",questionsList);
		model.addAttribute("category", category);

		return "questionslist";
	}
	
	/******* Return a single question *******/
	
	@GetMapping(value = "/question/{questionID}")
	public String showQuestion(Model model, @PathVariable("questionID") Integer questionID,
	HttpServletRequest request, @ModelAttribute("forecast") Forecast forecast, @ModelAttribute("comment") Comment comment) {
		
		HttpSession session = request.getSession(false);			
		User tmpUser = (User) session.getAttribute("user");
	
		Question question = questionService.findById(questionID);
		User user = userService.findById(tmpUser.getId());
		
		Share share = shareService.findByUserAndQuestion(user, question);
		Double yesShareValue = questionService.calculateShareValue(question);

		Optional<Question> test = questionService.findByIdAndFollowed_Id(questionID, user.getId());
		if(test.isEmpty())
			model.addAttribute("isFollowed", 0);
		else
			model.addAttribute("isFollowed", 1);

		model.addAttribute("question",question);
		model.addAttribute("yesQuantity", share.getYesShareQnt());	
		model.addAttribute("noQuantity", share.getNoShareQnt());	
		model.addAttribute("yesValue", yesShareValue);
		model.addAttribute("noValue", 1 - yesShareValue);
		model.addAttribute("commentsList", question.getComments());	

		return "question";	  
	}

	/******* Post a comment *******/
	
	@PostMapping(value = "/question/{questionID}/comments")
	public String postComment(HttpServletRequest request, @ModelAttribute("comment") Comment comment, @PathVariable Integer questionID) {   
		commentService.save(comment);
		HttpSession session = request.getSession(false);			
		User tmpUser = (User) session.getAttribute("user");

		Question question = questionService.findById(questionID);
		User user = userService.findById(tmpUser.getId());
		
		notificationService.sendCommentNotification(user, question);
		
		return "redirect:/question/"+questionID;	  
	}
    
	/******* Follow a question *******/
	
	@PostMapping(value = "/question/{questionID}/follow")
	@ResponseBody
	public void followQuestion(HttpServletRequest request, @RequestParam String type, @PathVariable Integer questionID) {   
  		
		HttpSession session = request.getSession(false);	
    	User tmpUser = (User) session.getAttribute("user");
    	
    	Question question = questionService.findById(questionID);
    	User user = userService.findById(tmpUser.getId());
		
    	questionService.updateFollow(type, question, user);  			  
	}
	
	/******* Close a question *******/
	
	@PostMapping(value = "/question/{questionID}/close")
	public String closeQuestion(@PathVariable Integer questionID, @RequestParam("winnerType") String winnerType) {
		Question question = questionService.findById(questionID);
    	
		questionService.closeQuestion(question, winnerType);	
		return "redirect:/questions/all";	
	}
}