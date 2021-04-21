
package com.sticast.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 10)
	private Integer id;

	@Column(name = "username", unique = true, nullable = false)
	private String userName;

	@Column(name = "password", nullable=false)
	private String password;

	@Column(name = "email", unique=true, nullable=false, length=100)
	private String email;
	
	@Column(name= "budget", nullable=false, precision=22)
	private double budget;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Forecast> forecasts;
	
	@Column(name = "status", nullable=false, length=100)
	private String status;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "follow", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "question_id"))
	private Set<Question> follows = new HashSet<Question>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="user", optional = false)
	private UserDetails userDetails;
}