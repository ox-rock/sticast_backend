package com.sticast.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticast.entity.Category;
import com.sticast.repository.CategoryDao;
import com.sticast.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> findAll() {	
			return categoryDao.findAll();
	}
	
	public Category findByName(String category) {	
		return categoryDao.findByName(category);
	}
}