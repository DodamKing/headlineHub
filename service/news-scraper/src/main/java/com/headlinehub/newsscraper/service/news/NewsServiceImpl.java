package com.headlinehub.newsscraper.service.news;

import org.springframework.stereotype.Service;

import com.headlinehub.newsscraper.model.News;
import com.headlinehub.newsscraper.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News findByuserObjectId(String userObjectId) {
        return newsRepository.findByuserObjectId(userObjectId);
    }
    
}
