package com.sticast.service.impl;

import com.sticast.entity.AccountStatus;
import com.sticast.entity.MyUserPrincipal;
import com.sticast.entity.Role;
import com.sticast.entity.User;
import com.sticast.repository.RoleDao;
import com.sticast.repository.UserDetailsDao;
import com.sticast.repository.UserDao;
import com.sticast.service.UserService;
import com.sticast.validation.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	//LASCIARE FIELD INJECTION

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDetailsDao userDetailsDao;

	//TODO Fare in modo di poterlo chiamare come Bean evitando la circular dependency
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional
	public User findById(Integer Id) {
		// check the database if the user already exists
		return userDao.findById(Id).get();
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUsername(crmUser.getUsername());
		user.setPassword(encoder().encode(crmUser.getPassword()));
		user.setEmail(crmUser.getEmail());
		user.setStatus("inactive");
		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));

		// save user in the database
		userDao.save(user);
	}
	
	@Override
	@Transactional
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	@Transactional
	public void deleteByUsername(String username) {
		userDao.deleteByUsername(username);
	}

	@Override
	public User updateBudget(User user, Double payout) {
		user.setBudget(user.getBudget() - payout);
		return userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username");
		}

		boolean accountStatus;
		if(user.getStatus().equals("active"))
			accountStatus = true;
		else
			accountStatus = false;

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), accountStatus, true, true, true,
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}