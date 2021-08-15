package com.sticast.repository;

import com.sticast.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsDao extends JpaRepository<UserDetails, Integer> {

}
