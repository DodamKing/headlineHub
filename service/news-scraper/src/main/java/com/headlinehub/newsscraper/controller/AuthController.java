package com.headlinehub.newsscraper.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public void signup(@RequestBody Member member) {
        System.out.println("들어오긴 하냐");
        System.out.println(member);
        memberService.saveMember(member);
    }
    

    @GetMapping("/user-list")
    public List<Member> memberList() {
        List<Member> allMembers = memberService.findAllUser();
        return allMembers;
    }

    @GetMapping("/check-duplicate-id")
    public boolean checkDuplicateId(@RequestParam String userId) {
        Member member = memberService.findByUserId(userId);
        boolean result = member != null ? true : false;
        return result;
    }

    @GetMapping("/send-code")
    public boolean sendCode(@RequestParam String phoneNumber, HttpSession session) {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        String verificationCode = Integer.toString(code);

        //보냈다 치고
        session.setAttribute("verificationCode", verificationCode);
        System.out.println("code = " + session.getAttribute("verificationCode"));

        return true;
    }
    
    
    @GetMapping("/check-code")
    public boolean checkCode(@RequestParam String verificationCode, HttpSession session) {
        String sessionVerificationCode = (String) session.getAttribute("verificationCode");
        boolean isVerification = verificationCode.equals(sessionVerificationCode) ? true : false;
        return isVerification;
    }
    
    @GetMapping("/remove-session")
    public void removeSession(HttpSession session) {
        session.removeAttribute("verificationCode");
    }
    

}
