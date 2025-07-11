package com.example.case4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }

    @GetMapping("/")
    public String adminDashboard() {
        return "admin/dashboard";
    }
}


