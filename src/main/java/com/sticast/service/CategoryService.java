package com.sticast.service;

import java.util.List;
import com.sticast.entity.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
	public List<Category> findAll();
	public Category findByName(String category);
}
