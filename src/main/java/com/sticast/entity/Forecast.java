package com.sticast.entity;

import java.util.Date;
import javax.persistence.*;
import com.sticast.entity.enumeration.Answer;
import com.sticast.entity.enumeration.Type;
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
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Answer answer;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Type type;
    
    @Column(nullable=false)
    private Integer quantity;
    
    @Column(nullable=false)
    private Double payout;
    
    @Column(nullable=false)
    @CreationTimestamp
    private Date timestamp;
}