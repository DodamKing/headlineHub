package com.headlinehub.newsscraper.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.headlinehub.newsscraper.model.News;

public interface NewsRepository extends MongoRepository<News, String> {
    News findByuserObjectId(String userObjectId);
}
