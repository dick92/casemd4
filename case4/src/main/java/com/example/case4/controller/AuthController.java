package com.example.case4.controller;

import com.example.case4.Service.AuthService;
import com.example.case4.model.Account;
import com.example.case4.model.LoginRequest;
import com.example.case4.model.RegisterRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("registerRequest") RegisterRequest request, Model model) {
        try {
            authService.registerCustomer(request);
            model.addAttribute("success", "Đăng ký thành công!");
            return "register";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            return "register";
        }
    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginRequest") LoginRequest request,
                               Model model, HttpSession session) {
        Account account = authService.authenticate(request.getUsername(), request.getPassword());
        if (account != null) {
            session.setAttribute("loggedInUser", account);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
            return "login";
        }
    }

    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("loggedInUser");
        if (account == null) return "redirect:/login";

        model.addAttribute("username", account.getUsername());
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}


