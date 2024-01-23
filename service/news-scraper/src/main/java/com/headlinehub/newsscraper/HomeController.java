package com.headlinehub.newsscraper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;





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
    
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "thsi is test page!";
    }
    
}
