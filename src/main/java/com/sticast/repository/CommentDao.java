package com.sticast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Comment;

@Repository("CommentDao")
public interface CommentDao extends JpaRepository<Comment, Integer> {

}
