package com.example.case4.controller;

import com.example.case4.model.Apartment;
import com.example.case4.service.ApartmentViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentViewService apartmentService;

    @GetMapping
    public String listApartments(Model model) {
        model.addAttribute("apartments", apartmentService.findAllApartments());
        return "apartment/list";
    }

    @GetMapping("/{id}/services")
    public String viewApartmentServices(@PathVariable Integer id, Model model) {
        Optional<Apartment> apartmentOpt = apartmentService.findApartmentById(id);
        if (apartmentOpt.isEmpty()) return "redirect:/admin/apartments";

        Apartment apartment = apartmentOpt.get();
        model.addAttribute("apartment", apartment);
        model.addAttribute("services", apartment.getApartmentServices());

        return "apartment/services";
    }
}
