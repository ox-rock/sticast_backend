package com.sticast.repository;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Question;

@Repository("QuestionDao")
public interface QuestionDao extends JpaRepository<Question, Integer> {
	 ArrayList<Question> findAll();
	 Optional<Question> findById(Integer id);
}