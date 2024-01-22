package com.headlinehub.newsscraper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.headlinehub.newsscraper.model.Member;




@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
    
    @GetMapping("/signup")
    public String signup() {
        return "signup.html";
    }
}
