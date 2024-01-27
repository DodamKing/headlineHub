package com.headlinehub.newsscraper.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.headlinehub.newsscraper.model.News;

public interface NewsRepository extends MongoRepository<News, String> {

    @Query("{ 'userObjectId' : ?0}")
    List<News> findByUserObjectId(String userObjectId);

    News findByLink(String link);
}
