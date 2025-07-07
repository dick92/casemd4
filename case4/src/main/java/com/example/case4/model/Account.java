package com.example.case4.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Customer customer;

    public enum Role {
        CUSTOMER, ADMIN, STAFF
    }

    public enum Status {
        ACTIVE, LOCKED
    }
}

