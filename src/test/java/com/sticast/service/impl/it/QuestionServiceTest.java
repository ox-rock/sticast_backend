package com.sticast.service.impl.it;

import com.sticast.conf.SpringBootConfig;
import com.sticast.entity.Question;
import com.sticast.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SpringBootConfig.class)
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Test
    public void findByIdTest_TC0(){
        Question question = questionService.findById(1);
        assertTrue(question.getText().equals("Prova domanda 1"));
    }

    @Test
    public void findByIdTest_TC1(){
        assertThrows(ResponseStatusException.class, () -> questionService.findById(-1));
    }
}