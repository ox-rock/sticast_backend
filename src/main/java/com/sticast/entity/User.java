package com.sticast.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
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

	@Override
	public String toString() {
		return "User {" +
				"id=" + id +
				", username='" + username + '\'' +
				'}';
	}
}