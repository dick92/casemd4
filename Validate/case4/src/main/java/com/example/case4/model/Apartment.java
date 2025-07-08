package com.example.case4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

    @OneToMany(mappedBy = "apartment")
    private List<ApartmentService> apartmentServices;

    @OneToMany(mappedBy = "apartment")
    private List<ApartmentRental> rentals;

    public enum Status {
        AVAILABLE,
        RENTED
    }
}
