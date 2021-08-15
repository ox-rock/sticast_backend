package com.sticast.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sticast.entity.Category;
import com.sticast.repository.CategoryDao;
import com.sticast.service.CategoryService;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> findAll() {	
			return categoryDao.findAll();
	}
	
	public Category findByName(String category) {
		Optional<Category> cat = categoryDao.findByName(category);

		if(cat.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This question doesn't exists");

		return cat.get();
	}
}