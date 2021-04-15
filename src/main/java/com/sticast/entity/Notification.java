package com.sticast.entity;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;
import java.util.Date;

@Data
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
}