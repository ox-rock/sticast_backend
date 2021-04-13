package com.sticast.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Column(name = "first_name", nullable=false, length=100)
	private String firstName;

	@Column(name = "last_name", nullable=false, length=100)
	private String lastName;

	@Column(name = "email", unique=true, nullable=false, length=100)
	private String email;
	
	@Column(name= "budget", nullable=false, precision=22)
	private double budget;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Forecast> forecasts;
	
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

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private UserSettings userSettings;
	
	/*******************************
	 *        Constructors         *
	 *******************************/
	
	 public User() {
		 
	 }
	 
	 public User(Integer id, String userName, String password, String firstName, String lastName, String email,
				double budget, Collection<Role> roles) {
			super();
			this.id = id;
			this.userName = userName;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.budget = budget;
			this.roles = roles;
		}

	 public User(Integer id, String userName, String password, String firstName, String lastName, String email,
				double budget, List<Forecast> forecasts, Collection<Role> roles, Set<Question> follows,
				UserSettings userSettings) {
			super();
			this.id = id;
			this.userName = userName;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.budget = budget;
			this.forecasts = forecasts;
			this.roles = roles;
			this.follows = follows;
			this.userSettings = userSettings;
		}


	/*******************************
	 *     Getters & Setters       *
	 *******************************/
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public double getBudget() {
			return budget;
		}

		public void setBudget(double budget) {
			this.budget = budget;
		}

		public Collection<Role> getRoles() {
			return roles;
		}

		public void setRoles(Collection<Role> roles) {
			this.roles = roles;
		}

		
		public UserSettings getUserSettings() {
			return userSettings;
		}

		public void setUserSettings(UserSettings userSettings) {
			this.userSettings = userSettings;
		}

		public List<Forecast> getForecasts() {
			return forecasts;
		}

		public void setForecasts(List<Forecast> forecasts) {
			this.forecasts = forecasts;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
					+ ", lastName=" + lastName + ", email=" + email + ", budget=" + budget + "]";
		}

		public Set<Question> getFollows() {
			return follows;
		}

		public void setFollows(Set<Question> follows) {
			this.follows = follows;
		}

}
