package com.sticast.entity;

import lombok.Data;

@Data
public class JwtResponse {
    private Integer userId;
    private String username;
    private Double userBudget;
    private String token;
    private Integer expirationTime;
    private Integer status;
}
