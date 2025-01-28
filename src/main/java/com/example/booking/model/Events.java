package com.example.booking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId ;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable =  false , length = 500)
    private String desc ;

    @Column(nullable = false)
    private String location ;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date eventDate ;

    @Column(nullable = false)
    private double price ;

    @Column(nullable = false)
    private Integer availableSeats;
}
