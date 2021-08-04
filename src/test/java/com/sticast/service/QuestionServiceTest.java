package com.sticast.service;

import com.sticast.entity.Question;
import com.sticast.repository.QuestionDao;
import com.sticast.service.QuestionService;
import com.sticast.service.impl.QuestionServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
    @InjectMocks
    private QuestionServiceImpl questionServiceImpl;

    @Mock
    private Question question;

    @Test
    void calculateShareValue_TC0() {
        when(question.getYesShareQuantity()).thenReturn(50);
        when(question.getNoShareQuantity()).thenReturn(0);

        assertEquals(0.62, questionServiceImpl.calculateShareValue(question));
    }
}