package com.example.booking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId ;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable =  false , length = 500)
    private String description ;

    @Column(nullable = false)
    private String location ;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date eventDate ;

    @Column(nullable = false)
    private double price ;

    @Column(nullable = false)
    private Integer availableSeats;

    @ManyToMany(mappedBy = "events") // Inverse side of the relationship
    private Set<Bookings> bookings = new HashSet<>();

}
