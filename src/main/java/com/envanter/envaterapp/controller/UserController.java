package com.envanter.envaterapp.controller;

import com.envanter.envaterapp.model.User;
import com.envanter.envaterapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Kayıt formunu göster
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Kayıt formunu işle
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("message", "Bu kullanıcı adı zaten mevcut!");
            return "register";
        }

        userService.saveUser(user);
        model.addAttribute("message", "Kayıt başarılı! Giriş yapabilirsiniz.");
        return "login";
    }

    // Giriş formunu göster
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    // Giriş işlemi
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, Model model) {
        return userService.findByUsername(user.getUsername())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .map(u -> "redirect:/home")
                .orElseGet(() -> {
                    model.addAttribute("message", "Kullanıcı adı veya şifre yanlış!");
                    return "login";
                });
    }

    // Anasayfa
    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

}
