package com.sticast.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Category;

@Repository("CategoryDao")
public interface CategoryDao extends JpaRepository<Category, Integer> {

	List<Category> findAll();
	Category findByName(String category);
}
