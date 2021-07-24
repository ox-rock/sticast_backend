package com.sticast.controller.rest;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sticast.entity.*;
import com.sticast.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@SessionAttributes("user")
@RequestMapping("/api")
public class QuestionRestController {
    private UserService userService;
    private QuestionService questionService;
    private NotificationService notificationService;
    private ForecastService forecastService;
    private CommentService commentService;
    private CategoryService categoryService;
    private UserQuestionDetailsService userQuestionDetailsService;

    @Autowired
    public QuestionRestController(UserService userService,
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

    // Get all questions
    // TODO Implement search functionality: by name, date and forecasts number with pagination
    @GetMapping(value = "/questions")
    public ResponseEntity<List<Question>> showAllQuestions(Model model, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(questionService.findAll());
    }

    // TODO Implement multiple question category support
    // Get all questions of a given category
    @GetMapping(value = "/questions/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.findByName(category).getQuestions());
    }

    // Get a single question
    @GetMapping(value = "/question/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(questionService.findById(id));
    }

    // TODO Pensare un nome migliore
    @GetMapping(value = "/question/{id}/data")
    public ResponseEntity<UserQuestionDetails> getShares(HttpServletRequest request,
                                                         @PathVariable Integer id) {

        HttpSession session = request.getSession(false);
        User tmpUser = (User) session.getAttribute("user");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userQuestionDetailsService.findByUser_IdAndQuestion_Id(tmpUser.getId(), id));
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