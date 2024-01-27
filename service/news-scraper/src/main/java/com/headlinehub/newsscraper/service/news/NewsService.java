package com.headlinehub.newsscraper.service.news;

import java.util.List;

import com.headlinehub.newsscraper.model.News;

public interface NewsService {
    News saveNews(News news);
    List<News> findByUserObjectId(String userObjectId);
    News findByLink(String link);
}
