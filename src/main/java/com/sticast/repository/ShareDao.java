package com.sticast.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sticast.entity.Question;
import com.sticast.entity.Share;
import com.sticast.entity.ShareKey;
import com.sticast.entity.User;

@Repository("ShareRepository")
public interface ShareDao extends JpaRepository<Share, ShareKey> {

	Optional<Share> findByUserAndQuestion(User user, Question question);
	List<Share> findByQuestion(Question question);
}
