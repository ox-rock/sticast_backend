package com.sticast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticast.entity.Question;
import com.sticast.entity.Share;
import com.sticast.entity.User;
import com.sticast.repository.ShareDao;
import com.sticast.service.ShareService;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
	private ShareDao shareRepository;
    	
    @Override
	public Share findByUserAndQuestion(User user, Question question) {
		return shareRepository.findByUserAndQuestion(user, question).orElse(new Share(null));
	}
}