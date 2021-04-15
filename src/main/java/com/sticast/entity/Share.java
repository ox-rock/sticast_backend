package com.sticast.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;

@Data
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
	
	public Share(Object object) {
		yesShareQnt=0;
		noShareQnt=0;
	}
}