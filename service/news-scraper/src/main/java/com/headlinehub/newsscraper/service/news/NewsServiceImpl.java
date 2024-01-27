package com.headlinehub.newsscraper.service.news;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

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
    public News saveNews(News news) {
        Instant currentInstant = Instant.now();
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime koreaDateTime = currentInstant.atZone(koreaZoneId);
        news.setCreatedAt(koreaDateTime.toInstant());
        return newsRepository.save(news);
    }

    @Override
    public List<News> findByUserObjectId(String userObjectId) {
        return newsRepository.findByUserObjectId(userObjectId);
    }

    @Override
    public News findByLink(String link) {
        return newsRepository.findByLink(link);
    }
}
