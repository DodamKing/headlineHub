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
    public void memberCRD() {
        Member member = new Member();
        member.setUserId("testuser100");
        member.setPassword("testPassword100");
        member.setPhoneNumber("01011112222");

        Member saveMember = memberRepository.save(member);
        
        assertThat(saveMember.getId()).isNotNull();
        assertThat(saveMember.getUserId()).isEqualTo(member.getUserId());
        assertThat(saveMember.getPassword()).isEqualTo(member.getPassword());

        Member foundMember = memberRepository.findByUserId(member.getUserId());
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getPassword()).isEqualTo(member.getPassword());

        memberRepository.delete(member);
    }
}