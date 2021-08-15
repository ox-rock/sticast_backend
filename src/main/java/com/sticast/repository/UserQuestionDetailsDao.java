package com.sticast.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Question;
import com.sticast.entity.UserQuestionDetails;
import com.sticast.entity.ShareKey;
import com.sticast.entity.User;

@Repository("ShareRepository")
public interface UserQuestionDetailsDao extends JpaRepository<UserQuestionDetails, ShareKey> {
	Optional<UserQuestionDetails> findByUserAndQuestion(User user, Question question);
	List<UserQuestionDetails> findByQuestion(Question question);
    UserQuestionDetails findByUser_IdAndQuestion_Id(Integer id, Integer id1);
}
