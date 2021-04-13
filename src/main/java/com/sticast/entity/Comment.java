package com.sticast.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="comment")
public class Comment {


	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, length=10)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;
    
    @Column(name="text", nullable=false, length=500)
    private String text;
    
    @Column(name="timestamp", nullable=false)
    @CreationTimestamp
    private Date timestamp;
    
	/******* Constructors *******/
    
    public Comment() {
        super();
    }
    
    public Comment(Integer id, User user, Question question, String text, Date timestamp) {
		super();
		this.id = id;
		this.user = user;
		this.question = question;
		this.text = text;
		this.timestamp = timestamp;
	}

    /******* Getters and Setters *******/
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer aId) {
        id = aId;
    }

    public String getText() {
        return text;
    }

    public void setText(String aText) {
        text = aText;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}