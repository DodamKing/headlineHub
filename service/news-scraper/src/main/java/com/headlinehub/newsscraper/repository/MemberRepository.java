package com.headlinehub.newsscraper.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.headlinehub.newsscraper.model.Member;

public interface MemberRepository extends MongoRepository<Member, String> {
    Member findByUserId(String userId);
}
