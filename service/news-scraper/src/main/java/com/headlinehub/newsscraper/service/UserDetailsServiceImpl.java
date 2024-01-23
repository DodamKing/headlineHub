package com.headlinehub.newsscraper.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.headlinehub.newsscraper.model.Member;
import com.headlinehub.newsscraper.repository.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final MemberRepository memberRepository;

    public UserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(userId);
        if (member == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.: "  + userId);
        }
        return new CustomUser(member);
    }
}
