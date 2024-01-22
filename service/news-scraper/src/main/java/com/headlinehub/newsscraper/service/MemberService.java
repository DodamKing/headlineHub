package com.headlinehub.newsscraper.service;

import java.util.List;

import com.headlinehub.newsscraper.model.Member;

public interface MemberService {
    void saveMember(Member member);
    Member findByUserId(String userId);
    List<Member> findAllUser();
}
