
package com.sticast.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    
    @Column(name="comment_notification")
    private Integer commentNotification;
    
    @Column(name="closed_question_notification")
    private Integer closedQuestionNotification;
    
    @Column(name="created_at", nullable=false)
    @CreationTimestamp
    private Date createdAt;
    
    @Column(name="last_login", nullable=false)
    @CreationTimestamp
    private Date lastLogin;
    
    @Column(name="last_IP", nullable=false)
    private String lastIP;
    
    @Column(name="is_2FA_active", nullable=false)
    private Boolean is2FAActive;
}