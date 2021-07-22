package com.sticast.entity;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false, length=100)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable=false, length=100)
	private String email;
	
	@Column(nullable = false)
	private double budget;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Forecast> forecasts;
	
	@Column(nullable=false, length=100)
	private String status;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="user", optional = false)
	private UserDetails userDetails;
}