package com.example.case4.repository;

import com.example.case4.model.ApartmentRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRentalRepository extends JpaRepository<ApartmentRental, Integer> {
}
