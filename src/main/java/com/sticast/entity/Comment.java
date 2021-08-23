package com.sticast.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Data
@Entity
public class Comment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnoreProperties({"password", "budget", "status", "roles", "id"})
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;
    
    @Column(nullable = false, length=500)
    private String text;
    
    @Column(nullable = false)
    @CreationTimestamp
    private Date timestamp;
}