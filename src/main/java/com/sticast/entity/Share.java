package com.sticast.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="share")
public class Share {

	@EmbeddedId
	private ShareKey id;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@MapsId("questionId")
	@JoinColumn(name = "question_id")
	private Question question;

	@Column(name="yes_share_quantity", nullable=false, length=5)
	private Integer yesShareQnt;
	
	@Column(name="no_share_quantity", nullable=false, length=5)
	private Integer noShareQnt;

	public Share () {
		super();
	}
	
	public Share(ShareKey id, User user, Question question, Integer yesShareQnt, Integer noShareQnt) {
		super();
		this.id = id;
		this.user = user;
		this.question = question;
		this.yesShareQnt = yesShareQnt;
		this.noShareQnt = noShareQnt;
	}
	
	public Share(Object object) {
		yesShareQnt=0;
		noShareQnt=0;
	}

	public ShareKey getId() {
		return id;
	}

	public void setId(ShareKey id) {
		this.id = id;
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

	public Integer getYesShareQnt() {
		return yesShareQnt;
	}

	public void setYesShareQnt(Integer yesShareQnt) {
		this.yesShareQnt = yesShareQnt;
	}

	public Integer getNoShareQnt() {
		return noShareQnt;
	}

	public void setNoShareQnt(Integer noShareQnt) {
		this.noShareQnt = noShareQnt;
	}
}