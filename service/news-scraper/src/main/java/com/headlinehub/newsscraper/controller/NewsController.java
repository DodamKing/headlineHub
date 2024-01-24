package com.headlinehub.newsscraper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public News scrappedNews(@PathVariable String userObjectId) {
        return newsService.findByuserObjectId(userObjectId);
    }
    
}
