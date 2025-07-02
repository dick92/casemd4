package com.example.case4.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cccd;

    private String email;

    private String phone;

    private String password;

    @OneToOne
    @JoinColumn(name = "rented_apartment_id")
    private CanHo canHo;

}
