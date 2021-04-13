package com.sticast.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="user_settings")
public class UserSettings {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, length=10)
    private Integer id;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name="comment_notification")
    private Integer commentNotification;
    
    @Column(name="closed_question_notification")
    private Integer closedQuestionNotification;

    public UserSettings(Integer id, User user, Integer commentNotification, Integer closedQuestionNotification) {
		super();
		this.id = id;
		this.user = user;
		this.commentNotification = commentNotification;
		this.closedQuestionNotification = closedQuestionNotification;
	}
    
    public UserSettings() {
		super();
	}
  
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommentNotification() {
		return commentNotification;
	}

	public void setCommentNotification(Integer commentNotification) {
		this.commentNotification = commentNotification;
	}

	public Integer getClosedQuestionNotification() {
		return closedQuestionNotification;
	}

	public void setClosedQuestionNotification(Integer closedQuestionNotification) {
		this.closedQuestionNotification = closedQuestionNotification;
	}
    

}