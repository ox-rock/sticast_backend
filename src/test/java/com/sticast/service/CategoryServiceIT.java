package com.sticast.service;

import com.sticast.configurations.springboot.SpringBootConfig;
import com.sticast.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SpringBootConfig.class)
public class CategoryServiceIT {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByName_TC0(){
        Category category = categoryService.findByName("sport");
        assertTrue(category.getName().equals("sport"));
    }

    @Test
    public void findByName_TC1(){
        assertThrows(ResponseStatusException.class, () -> categoryService.findByName("acaso"));
    }
}
