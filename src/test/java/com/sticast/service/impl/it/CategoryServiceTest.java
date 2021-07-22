package com.sticast.service.impl.it;

import com.sticast.conf.SpringBootConfig;
import com.sticast.entity.Category;
import com.sticast.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SpringBootConfig.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByNameTest_TC0(){
        Category category = categoryService.findByName("sport");
        assertTrue(category.getName().equals("sport"));
    }

    @Test
    public void findByNameTest_TC1(){
        assertThrows(ResponseStatusException.class, () -> categoryService.findByName("acaso"));
    }
}
