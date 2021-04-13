package com.sticast.service.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticast.entity.Comment;
import com.sticast.repository.CommentDao;
import com.sticast.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
	private CommentDao commentDao;

	@Transactional
	@Override
	public void save(Comment comment) {
		commentDao.save(comment);	
	}
	
	//Category category_o = categoryRepository.findByName(category);
   // return category_o.getQuestions();
}