package com.headlinehub.newsscraper.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.headlinehub.newsscraper.model.Member;
import com.headlinehub.newsscraper.repository.MemberRepository;

/**
 * MemberRepositoryTest
 */
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    
    @Test
    void memberCRD() {
        Member member = new Member();
        member.setUsername("testuser");
        member.setPassword("testPassword");

        Member saveMember = memberRepository.save(member);
        
        assertThat(saveMember.getId()).isNotNull();
        assertThat(saveMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(saveMember.getPassword()).isEqualTo(member.getPassword());

        Member foundMember = memberRepository.findByUsername(member.getUsername());
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getPassword()).isEqualTo(member.getPassword());

        memberRepository.delete(member);
    }
}