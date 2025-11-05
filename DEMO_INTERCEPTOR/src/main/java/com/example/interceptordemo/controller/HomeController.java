package com.example.interceptordemo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        String user = (String) session.getAttribute("user");
        model.addAttribute("username", user);
        return "home";
    }
}
