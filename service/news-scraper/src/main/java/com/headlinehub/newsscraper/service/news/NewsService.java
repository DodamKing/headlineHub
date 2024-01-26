package com.headlinehub.newsscraper.service.news;

import com.headlinehub.newsscraper.model.News;

public interface NewsService {
    News saveNews(News news);
    News findByuserObjectId(String userObjectId);
}
