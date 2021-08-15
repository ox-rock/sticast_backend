package com.sticast.service;

import com.sticast.entity.Question;
import com.sticast.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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