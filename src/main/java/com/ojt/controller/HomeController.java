package com.ojt.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    HttpSession httpSession;
    @GetMapping("/")
    public String home(){
        if (httpSession.getAttribute("userLogin") != null){
            return "home/index";
        }
        return "redirect:/login";
    }
}
