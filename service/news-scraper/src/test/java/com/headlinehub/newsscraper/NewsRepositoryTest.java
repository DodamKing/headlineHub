package com.headlinehub.newsscraper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.headlinehub.newsscraper.model.News;
import com.headlinehub.newsscraper.repository.NewsRepository;


@SpringBootTest
@SuppressWarnings("all")
public class NewsRepositoryTest {
    
    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void newsCreateTest() {
        News news = new News();
        news.setUserObjectId("65b12ff62ed75a6d6dd423ae");
        news.setHeadLine("test head line");
        news.setLink("가짜 링크");
        news.setContent("테스트를 위한 더미 내용");

        News savedNews = newsRepository.save(news);

        Assertions.assertTrue(newsRepository.existsById(savedNews.getId()));
    }

    @Test
    public void NewsReadTest() {
        News news = newsRepository.findByuserObjectId("65b12ff62ed75a6d6dd423ae");
        
        Assertions.assertNotNull(news);
    }
    
    @Test
    public void NewsDeleteTest() {
        News news = newsRepository.findByuserObjectId("65b12ff62ed75a6d6dd423ae");
        String id = news.getId();
        newsRepository.deleteById(id);
        Assertions.assertFalse(newsRepository.existsById(id));
    }
}
