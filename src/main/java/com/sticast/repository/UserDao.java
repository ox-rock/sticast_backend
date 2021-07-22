package com.sticast.repository;

import org.springframework.data.repository.CrudRepository;
import com.sticast.entity.User;

public interface UserDao extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User save(User user);
    void deleteByUsername(String username);
}
