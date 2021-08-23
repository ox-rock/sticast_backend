package com.sticast.controller;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.sticast.entity.*;
import com.sticast.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RolesAllowed("ROLE_USER")
@RequestMapping("/api")
public class QuestionController {
    private UserService userService;
    private QuestionService questionService;
    private NotificationService notificationService;
    private ForecastService forecastService;
    private CommentService commentService;
    private CategoryService categoryService;
    private UserQuestionDetailsService userQuestionDetailsService;

    @Autowired
    public QuestionController(UserService userService,
                              QuestionService questionService,
                              NotificationService notificationService,
                              CommentService commentService,
                              CategoryService categoryService,
                              ForecastService forecastService,
                              UserQuestionDetailsService userQuestionDetailsService) {
        this.userService = userService;
        this.questionService = questionService;
        this.notificationService = notificationService;
        this.commentService = commentService;
        this.forecastService = forecastService;
        this.categoryService = categoryService;
        this.userQuestionDetailsService = userQuestionDetailsService;
    }

    // TODO Implement search functionality: by name, date and forecasts number with pagination
    // TODO Implement multiple question category support
    @GetMapping(value = "/questions")
    public ResponseEntity<List<Question>> getQuestions(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "keyword", required = false) String keyword)
    {
        List<Question> questionsList;
        if (category.equals("all"))
            questionsList = questionService.findAll();
        else
            questionsList = categoryService.findByName(category).getQuestions();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(questionsList);
    }

    // Get a single question
    @GetMapping(value = "/question/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Integer id) {
        Question question = questionService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(question);
    }

    // TODO Think a better name
    @GetMapping(value = "/question/{id}/data")
    public ResponseEntity<UserQuestionDetails> getShares(HttpServletRequest request,
                                                         @PathVariable Integer id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userQuestionDetailsService.findByUser_UsernameAndQuestion_Id(SecurityContextHolder.getContext().getAuthentication().getName(), id));
    }

    //Get comments
    @GetMapping(value = "/question/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable Integer id) {

        Question question = questionService.findById(id);
        List<Comment> commentList = question.getComments();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentList);
    }

    //Post a comment
    @PostMapping(value = "/question/{id}/comment")
    public ResponseEntity postComment(HttpServletRequest request,
                                      @RequestBody Comment comment,
                                      @PathVariable Integer id) {
        commentService.save(comment);
        HttpSession session = request.getSession(false);
        User tmpUser = (User) session.getAttribute("user");

        Question question = questionService.findById(id);
        User user = userService.findById(tmpUser.getId());

        notificationService.sendCommentNotification(user, question);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("ok");
    }



    // Follow a question
    @PostMapping(value = "/question/{questionID}/follow")
    @ResponseBody
    public void followQuestion(HttpServletRequest request, @RequestParam String type, @PathVariable Integer questionID) {

        HttpSession session = request.getSession(false);
        User tmpUser = (User) session.getAttribute("user");

        Question question = questionService.findById(questionID);
        User user = userService.findById(tmpUser.getId());

        questionService.updateFollow(type, question, user);
    }

    // Close a question
    @PostMapping(value = "/question/{questionID}/close")
    public String closeQuestion(@PathVariable Integer questionID, @RequestParam("winnerType") String winnerType) {
        Question question = questionService.findById(questionID);

        questionService.closeQuestion(question, winnerType);
        return "redirect:/questions/all";
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> getCategories(Model model, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.findAll());
    }

    // TODO Implement AJAX
    @PostMapping(value = "/question/{id}")
    public ResponseEntity makeForecast(@PathVariable Integer id, HttpServletRequest request, @RequestBody Forecast tmpForecast) {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        Forecast forecast = forecastService.makeForecast(userId, id, tmpForecast);

        questionService.updateShareQuantity(forecast);

        session.setAttribute("user", userService.updateBudget(forecast.getUser(), forecast.getPayout()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Ok");
    }
}