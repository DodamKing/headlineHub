package com.headlinehub.newsscraper.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.headlinehub.newsscraper.model.Member;
import com.headlinehub.newsscraper.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole("ROLE_USER");

        String phoneNumber = member.getPassword();
        member.setPassword(phoneNumber.replaceAll("[-\\s]", ""));

        Instant currentInstant = Instant.now();
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime koreaDateTime = currentInstant.atZone(koreaZoneId);
        member.setLoginedAt(koreaDateTime.toInstant());

        memberRepository.save(member);
    }

    @Override
    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    @Override
    public List<Member> findAllUser() {
        return memberRepository.findAll();
    }
}
