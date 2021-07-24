package com.sticast.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.security.RolesAllowed;

@Controller
public class QuestionController {

    @GetMapping(value = "/questions")
    public String showAllQuestions() {
        return "questionslist";
    }

    @GetMapping(value = "/questions/{category}")
    public String showQuestionsByCategory(@PathVariable String category) {
        return "questionslist";
    }

    @GetMapping(value = "/question/{questionID}")
    public String showQuestion(@PathVariable("questionID") Integer questionID) {
        return "question";
    }

}