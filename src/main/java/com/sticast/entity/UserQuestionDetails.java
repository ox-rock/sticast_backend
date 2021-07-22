package com.sticast.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UserQuestionDetails {

	@JsonIgnore
	@EmbeddedId
	private ShareKey id;

	@JsonIgnore
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnore
	@ManyToOne
	@MapsId("questionId")
	@JoinColumn(name = "question_id")
	private Question question;

	@Column(name="yes_share_quantity", nullable=false)
	private Integer yesShareQnt;
	
	@Column(name="no_share_quantity", nullable=false)
	private Integer noShareQnt;

	@Column(name="isFollowed", nullable=false)
	private Boolean isFollowed;

	public UserQuestionDetails(Object object) {
		yesShareQnt=0;
		noShareQnt=0;
	}
}