package com.sticast.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.UserQuestionDetails;
import com.sticast.entity.User;
import com.sticast.repository.QuestionDao;
import com.sticast.repository.UserQuestionDetailsDao;
import com.sticast.repository.UserDao;
import com.sticast.service.QuestionService;
import org.springframework.web.server.ResponseStatusException;

@Service
public class QuestionServiceImpl implements QuestionService {
	private QuestionDao questionRepository;
   	private UserQuestionDetailsDao userQuestionDetailsDao;
   	private UserDao userDao;

   	@Autowired
	public QuestionServiceImpl(QuestionDao questionRepository, UserQuestionDetailsDao userQuestionDetailsDao, UserDao userDao) {
		this.questionRepository = questionRepository;
		this.userQuestionDetailsDao = userQuestionDetailsDao;
		this.userDao = userDao;
	}

	@Override
	public List<Question> findAll() {
			return questionRepository.findAll();
	}
	
	@Override
	public Question findById(Integer questionID) {
		Optional<Question> question = questionRepository.findById(questionID);
		if(question.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This question doesn't exists");

		return question.get();
	}
	
	@Override
	public Double calculateShareValue(Question question) {
		//yesPrice = exp(yesQuantity/100) / (exp(yesQuantity/100) + exp(noQuantity/100))
		Double yesPrice = (Math.exp(question.getYesShareQuantity()/100.0))/
				(Math.exp(question.getYesShareQuantity()/100.0)+Math.exp(question.getNoShareQuantity()/100.0));

		return Math.round(yesPrice * 100.0)/100.0;
	}

	@Override
	public void updateShareQuantity(Forecast forecast) {
		Question question = forecast.getQuestion();
		
		if(forecast.getAnswer().equals("yes")) 
		    question.setYesShareQuantity(question.getYesShareQuantity() + forecast.getQuantity());
		else 
			question.setNoShareQuantity(question.getNoShareQuantity() + forecast.getQuantity());
			
		questionRepository.save(question);
	}

	@Override
	public void updateFollow(String requestType, Question question, User user) {
	/*	List<User> followers = question.getFollowed();
		if(requestType.equals("follow"))
			followers.add(user);
		else
			followers.remove(user);
		questionRepository.save(question);
		*/
	}

	@Override
	public void closeQuestion(Question question, String winnerType) {
		Double yesValue = calculateShareValue(question);
		List <UserQuestionDetails> winners = userQuestionDetailsDao.findByQuestion(question);
		User user = new User();
		for (int i = 0; i < winners.size(); i++) {
			user = winners.get(i).getUser();
			user.setBudget(user.getBudget() + winners.get(i).getYesShareQnt()*yesValue);
			userDao.save(user);
		}
	}


}