package com.sticast.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable

public class ShareKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
    Integer userId;

    @Column(name = "question_id")
    Integer questionId;
    
}