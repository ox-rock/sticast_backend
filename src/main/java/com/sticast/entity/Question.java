package com.sticast.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

//TODO Add forecasters and forecasts number

@Data
@Entity
@Table(name = "question")
public class Question {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Integer id;
    
    @Column(name = "text", nullable = false, length = 300)   
    private String text;
    
    @Column(name = "yes_share", nullable = false, length = 10)
    private Integer yesShareQuantity;
    
    @Column(name = "no_share", nullable = false, length = 10)
    private Integer noShareQuantity;
    
    @Column(name = "is_open", nullable = false, length = 3)
    private Integer isOpen;
    
    @Column(name = "creation_date", nullable = false, length = 45)
    private String creationDate;
    
    @Column(name = "expiration_date", nullable = false, length = 45)
    private String expirationDate;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Comment> comments;
    
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "questions_categories", 
	joinColumns = @JoinColumn(name = "question_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;
    
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "follow", 
	joinColumns = @JoinColumn(name = "question_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> followed = new HashSet<User>();
}