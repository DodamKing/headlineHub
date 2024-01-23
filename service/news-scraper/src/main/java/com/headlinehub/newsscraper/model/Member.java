package com.headlinehub.newsscraper.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Member {
    @Id
    private String id;
    private String userId;
    private String password;
    private String phoneNumber;
    private String role;
    private Instant loginedAt;
    private Instant createdAt;
}
