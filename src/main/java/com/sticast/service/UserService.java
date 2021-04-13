package com.sticast.service;

import com.sticast.entity.User;
import com.sticast.user.CrmUser;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
    User findById(Integer Id);
    void save(CrmUser crmUser);
    void save(User user);

}
