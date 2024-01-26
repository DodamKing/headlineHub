package com.headlinehub.newsscraper.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class News {
    @Id
    private String id;
    private String userObjectId;
    private String headLine;
    private String link;
    private String content;
    private Instant createdAt;
}
