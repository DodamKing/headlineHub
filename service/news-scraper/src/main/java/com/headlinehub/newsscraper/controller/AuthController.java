package com.headlinehub.newsscraper.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.headlinehub.newsscraper.model.Member;
import com.headlinehub.newsscraper.service.MemberService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody Member member) {
        memberService.saveMember(member);
    }

    @GetMapping("/user-list")
    public List<Member> memberList() {
        List<Member> allMembers = memberService.findAllUser();
        return allMembers;
    }
    
}
