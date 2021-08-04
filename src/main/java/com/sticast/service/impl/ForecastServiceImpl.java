package com.sticast.service.impl;

import com.sticast.entity.Forecast;
import com.sticast.entity.Question;
import com.sticast.entity.User;
import com.sticast.entity.UserQuestionDetails;
import com.sticast.repository.ForecastDao;
import com.sticast.repository.QuestionDao;
import com.sticast.repository.UserDao;
import com.sticast.repository.UserQuestionDetailsDao;
import com.sticast.service.ForecastService;
import com.sticast.service.UserQuestionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class ForecastServiceImpl implements ForecastService {
    private UserDao userDao;
    private QuestionDao questionDao;
    private ForecastDao forecastDao;
    private UserQuestionDetailsService userQuestionDetailsService;
    private UserQuestionDetailsDao userQuestionDetailsDao;

    @Autowired
    public ForecastServiceImpl(UserDao userDao,
                               QuestionDao questionDao,
                               ForecastDao forecastDao,
                               UserQuestionDetailsService userQuestionDetailsService,
                               UserQuestionDetailsDao userQuestionDetailsDao) {
        this.userDao = userDao;
        this.questionDao = questionDao;
        this.forecastDao = forecastDao;
        this.userQuestionDetailsService = userQuestionDetailsService;
        this.userQuestionDetailsDao = userQuestionDetailsDao;
    }

    @Override
    public Double calculatePayout(Forecast forecast) {
        final double B = 100.0; //Maximum possible amount of money the market maker can lose

        if(forecast.getAnswer().equals("yes")) {
            return B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity() + forecast.getQuantity())/B) + Math.exp(forecast.getQuestion().getNoShareQuantity()/B))) -
                    B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity()/B)) + Math.exp(forecast.getQuestion().getNoShareQuantity()/B)));
        }
        else {
            return B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity())/B) + Math.exp((forecast.getQuestion().getNoShareQuantity() + forecast.getQuantity())/B))) -
                    B*Math.log((Math.exp((forecast.getQuestion().getYesShareQuantity())/B) + Math.exp(forecast.getQuestion().getNoShareQuantity()/B)));
        }
    }

    @Override
    public Forecast makeForecast(Integer userId, Integer questionId, Forecast forecast) {
        Optional<Question> question = questionDao.findById(questionId);
        if(question.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This question doesn't exists");
        forecast.setQuestion(question.get());

        Optional<User> user = userDao.findById(userId);
        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user doesn't exists");
        forecast.setUser(user.get());

        double payout = calculatePayout(forecast);
        Optional<UserQuestionDetails> userQuestionDetails =
                userQuestionDetailsDao.findByUserAndQuestion(user.get(), question.get());

        if(userQuestionDetails.isEmpty()) {
            userQuestionDetails.get().setQuestion(question.get());
            userQuestionDetails.get().setUser(user.get());
        }

        if (forecast.getType().equals("buy")) {
            if (payout > user.get().getBudget())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User's budget not sufficient for this forecast");
        } else {
            if (forecast.getAnswer().equals("yes")) {
                if (Math.abs(forecast.getQuantity()) > userQuestionDetails.get().getYesShareQnt())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
            } else {
                if (Math.abs(forecast.getQuantity()) > userQuestionDetails.get().getNoShareQnt())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
            }
        }

        forecast.setPayout(payout);
        userQuestionDetailsService.updateShareQuantity(userQuestionDetails.get(), forecast);

        return forecastDao.save(forecast);
    }

    @Override
    public List<Forecast> findByUsername_Id(com.sticast.entity.User user) {
        return forecastDao.findByUser(user);
    }
}