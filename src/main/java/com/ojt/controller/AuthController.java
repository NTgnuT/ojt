package com.ojt.controller;

import com.ojt.model.dto.request.SignInRequest;
import com.ojt.service.UserService.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public String goLoginPage(Model model){
        model.addAttribute("user", new SignInRequest());
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") SignInRequest user, RedirectAttributes redirectAttributes){
        if (userService.signIn(user)){
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("err", "Tên đăng nhập hoặc mật khẩu của bạn không khớp");
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        httpSession.removeAttribute("userLogin");
        return "redirect:/login";
    }


}
