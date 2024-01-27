package com.headlinehub.newsscraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.headlinehub.newsscraper.model.News;
import com.headlinehub.newsscraper.service.news.NewsService;



@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    
    @GetMapping("/{userObjectId}")
    public List<News> scrappedNews(@PathVariable String userObjectId) {
        // 유저 _id로 검색해서 news 리스트를 반환해야 할듯 다시 작성
        return newsService.findByUserObjectId(userObjectId);
    }
    
    @PostMapping("/{userObjectId}")
    public ResponseEntity<Integer> postMethodName(@RequestBody News news, @PathVariable String userObjectId) {
        News oldNews = newsService.findByLink(news.getLink());
        if (oldNews != null) return ResponseEntity.status(HttpStatus.CREATED).body(0);

        news.setUserObjectId(userObjectId);
        News createdNews = newsService.saveNews(news);
        if (createdNews != null) return ResponseEntity.status(HttpStatus.CREATED).body(1);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(2);
    }
    
}
