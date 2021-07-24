package com.sticast.service;

import com.sticast.entity.User;
import com.sticast.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User findById(Integer Id);
    void save(CrmUser crmUser);
    void save(User user);
    void deleteByUsername(String string);
    User updateBudget(User user, Double payout);
}