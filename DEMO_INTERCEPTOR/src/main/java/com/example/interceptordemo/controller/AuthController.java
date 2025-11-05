package com.example.interceptordemo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam(name = "username") String username,
                          @RequestParam(name = "password", required = false) String password,
                          HttpSession session) {
        if (username != null && !username.isBlank()) {
            session.setAttribute("user", username);
            return "redirect:/home";
        }
        return "redirect:/403";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "403";
    }
}
