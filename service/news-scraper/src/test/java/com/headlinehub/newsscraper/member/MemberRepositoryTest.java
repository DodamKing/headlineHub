package com.headlinehub.newsscraper.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.headlinehub.newsscraper.model.Member;
import com.headlinehub.newsscraper.repository.MemberRepository;

/**
 * MemberRepositoryTest
 */
@SpringBootTest
// @DataMongoTest
// @Import(MemberRepository.class)
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    // @Autowired
    // private MongoTemplate mongoTemplate;

    
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

        List<Member> allMembers = memberRepository.findAll();
        assertThat(allMembers).isNotNull();
        assertThat(allMembers).hasSize(2);
        
        memberRepository.delete(member);
        assertThat(memberRepository.findById(saveMember.getId())).isEmpty();

        // Member savedMember = mongoTemplate.save(member, "members");
        // Member retrievedMember = mongoTemplate.findById(savedMember.getId(), Member.class, "memebers");

        // assertEquals("testuser1", retrievedMember.getUsername());
        // assertEquals("testpassword", retrievedMember.getPassword());
    }
    
}