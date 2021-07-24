package com.sticast.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sticast.entity.enumeration.Status;
import lombok.Data;
import lombok.ToString;

//TODO Add forecasters and forecasts number

@Data
@Entity
@Table(name = "question")
public class Question {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 300)
    private String text;
    
    @Column(name = "yes_share", nullable = false, length = 10)
    private Integer yesShareQuantity;
    
    @Column(name = "no_share", nullable = false, length = 10)
    private Integer noShareQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;
    
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    
    @Column(name = "forecasters", nullable = false)
    private Integer forecasters;
    
    @Column(name = "forecasts", nullable = false)
    private Integer forecastsNumber;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Comment> comments;

    @JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "questions_categories", 
	joinColumns = @JoinColumn(name = "question_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;
}