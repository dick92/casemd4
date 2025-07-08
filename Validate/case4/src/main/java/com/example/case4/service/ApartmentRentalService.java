package com.example.case4.service;

import com.example.case4.model.ApartmentRental;
import com.example.case4.repository.ApartmentRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentRentalService {

    @Autowired
    private ApartmentRentalRepository repository;

    public ApartmentRental findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
    }
}
