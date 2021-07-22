package com.sticast.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Data
@Entity
public class Forecast {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;
    
    @Column(nullable=false, length=3)
    private String answer;
    
    @Column(nullable=false, length=10)
    private Integer quantity;
    
    @Column(nullable=false, length=10)
    private Double payout;
    
    @Column(nullable=false)
    @CreationTimestamp
    private Date timestamp;
}