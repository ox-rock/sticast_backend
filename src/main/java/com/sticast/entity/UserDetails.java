package com.sticast.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Data
@Entity
@Table(name="user_details")
public class UserDetails {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;
    
    @Column(name="comment_notification", nullable=false)
    private Boolean commentNotification = true;
    
    @Column(name="closed_question_notification", nullable=false)
    private Boolean closedQuestionNotification = true;
    
    @Column(name="created_at", nullable=false)
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name="last_login", nullable=false)
    @CreationTimestamp
    private LocalDateTime lastLogin = LocalDateTime.now();

    //TODO inserire IP in modo automatico
    @Column(name="last_IP", nullable=false)
    private String lastIP = "127.0.0.0";
    
    @Column(name="is_2FA_active", nullable=false)
    private Boolean is2FAActive = true;

}