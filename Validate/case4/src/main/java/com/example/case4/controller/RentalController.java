package com.example.case4.controller;

import com.example.case4.model.Apartment;
import com.example.case4.model.ApartmentRental;
import com.example.case4.model.ApartmentService;
import com.example.case4.service.ApartmentRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/rentals")
public class RentalController {

    @Autowired
    private ApartmentRentalService rentalService;

    @GetMapping("/{rentalId}/services")
    public List<ApartmentService> getServicesByRental(@PathVariable Integer rentalId) {
        ApartmentRental rental = rentalService.findById(rentalId);
        Apartment apartment = rental.getApartment();
        return apartment.getApartmentServices();
    }
}