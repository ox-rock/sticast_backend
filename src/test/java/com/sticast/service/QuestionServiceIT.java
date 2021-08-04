package com.sticast.service;

import com.sticast.configurations.springboot.SpringBootConfig;
import com.sticast.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SpringBootConfig.class)
public class QuestionServiceIT {

    @Autowired
    private QuestionService questionService;

    @Test
    public void findById_TC0(){
        Question question = questionService.findById(1);
        assertTrue(question.getText().equals("Prova domanda 1"));
    }

    @Test
    public void findById_TC1(){
        assertThrows(ResponseStatusException.class, () -> questionService.findById(-1));
    }
}