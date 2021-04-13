package com.sticast.entity;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 10)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="sender_user_id", nullable=false)
	private User sender;
	
	@ManyToOne
	@JoinColumn(name="reciever_user_id", nullable=false)
	private User reciever;
	
	@ManyToOne
	@JoinColumn(name="question_id", nullable=false)
	private Question question;
	
	@Column(name = "type")
	private String Type;

    @Column(name="timestamp", nullable=false)
    @CreationTimestamp
    private Date timestamp;
    
	@Column(name = "seen")
	private Integer Seen = 1;
	
	/*******************************
	 *        Constructors         *
	 *******************************/
	public Notification(Integer id, User sender, User reciever, Question question, String type, Date timestamp,
			Integer seen) {
		super();
		this.id = id;
		this.sender = sender;
		this.reciever = reciever;
		this.question = question;
		Type = type;
		this.timestamp = timestamp;
		Seen = seen;
	}

	public Notification() {
		super();
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


	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getSeen() {
		return Seen;
	}

	public void setSeen(Integer seen) {
		Seen = seen;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}

}
