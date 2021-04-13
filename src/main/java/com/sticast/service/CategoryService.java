package com.sticast.service;

import java.util.List;
import com.sticast.entity.Category;

public interface CategoryService {

	public List<Category> findAll();
	public Category findByName(String category);

}
