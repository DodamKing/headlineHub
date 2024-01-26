package com.headlinehub.newsscraper.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody Member member) {
        String userId = member.getUserId();
        String password = member.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        }
        catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody Member member) {
        try {
            memberService.saveMember(member);
            return ResponseEntity.ok(true);
        } 
        catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
    
    @GetMapping("/login-status")
    public ResponseEntity<Map<String, Object>> getSessioninfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Map<String, Object> response = new HashMap<>();
            String userId = ((UserDetails) authentication.getPrincipal()).getUsername().toString();
            response.put("userId", userId);
            response.put("_id", memberService.findByUserId(userId).getId());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(Collections.singletonMap("userId", "false"));
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
