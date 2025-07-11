package com.example.case4.service;

import com.example.case4.model.Apartment;
import com.example.case4.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentViewService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    public List<Apartment> findAllApartments() {
        return apartmentRepository.findAll();
    }

    public Optional<Apartment> findApartmentById(Integer id) {
        return apartmentRepository.findById(id);
    }
}

