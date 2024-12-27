package com.example.booking.model;

import jakarta.persistence.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

//    constructors for the user entity

    public Users(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
