package com.sticast.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.Share;
import com.sticast.entity.User;
import com.sticast.repository.QuestionDao;
import com.sticast.repository.ShareDao;
import com.sticast.repository.UserDao;
import com.sticast.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
	private QuestionDao questionRepository;
    
    @Autowired
   	private ShareDao shareDao;
    
    @Autowired
   	private UserDao userDao;
    	
	@Override
	public List<Question> findAll() {	
			return questionRepository.findAll();
	}
	
	@Override
	public Question findById(Integer questionID) {
		return questionRepository.findById(questionID).get();
	}
	
	@Override
	public Double calculateShareValue(Question question) {
		
		//yesPrice = exp(yesQuantity/100) / (exp(yesQuantity/100) + exp(noQuantity/100))
		Double yesPrice = (Math.exp(question.getYesShareQuantity()/100.0))/
				(Math.exp(question.getYesShareQuantity()/100.0)+Math.exp(question.getNoShareQuantity()/100.0));
		
		return yesPrice;
	}

	@Override
	public void updateShareQuantity(Forecast forecast) {
		Question oldQuestion = forecast.getQuestion();
		
		if(forecast.getAnswer().equals("yes")) 
		    oldQuestion.setYesShareQuantity(oldQuestion.getYesShareQuantity()+forecast.getQuantity()); 
		else 
			oldQuestion.setNoShareQuantity(oldQuestion.getNoShareQuantity() + forecast.getQuantity());
			
		questionRepository.save(oldQuestion);
	}

	@Override
	public void updateFollow(String requestType, Question question, User user) {
		
		Set<User> followers = question.getFollowed();
		if(requestType.equals("follow"))
			followers.add(user);
		else
			followers.remove(user);
		questionRepository.save(question);
	}

	@Override
	public Optional<Question> findByIdAndFollowed_Id(Integer questionid, Integer id) {
		return questionRepository.findByIdAndFollowed_Id(questionid, id);
	}

	@Override
	public void closeQuestion(Question question, String winnerType) {

		Double yesValue = calculateShareValue(question);
		List <Share> winners = shareDao.findByQuestion(question);	
		User user = new User();
		for (int i = 0; i < winners.size(); i++) {
			user = winners.get(i).getUser();
			user.setBudget(user.getBudget() + winners.get(i).getYesShareQnt()*yesValue);
			userDao.save(user);
		}
			
	}
}