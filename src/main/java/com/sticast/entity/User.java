package com.sticast.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Please insert your username")
	@Column(unique = true, nullable = false, length=100)
	private String username;

	@NotNull(message = "Please insert your password")
	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable=false, length=100)
	private String email;
	
	@Column(nullable = false)
	private double budget;

	@JsonIgnore
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

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="user", optional = false)
	private com.sticast.entity.UserDetails userDetails;


}