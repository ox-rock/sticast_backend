package com.sticast.repository;

import org.springframework.data.repository.CrudRepository;

import com.sticast.entity.Role;


public interface RoleDao extends CrudRepository<Role, Integer> {

	public Role findRoleByName(String theRoleName);
	
}
