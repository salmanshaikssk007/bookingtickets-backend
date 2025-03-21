package com.example.booking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "bookings")
public class Bookings implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private Users user ;

    @ManyToMany
    @JoinTable(
            name = "booking_events", // Join table name
            joinColumns = @JoinColumn(name = "booking_id"), // Foreign key to bookings
            inverseJoinColumns = @JoinColumn(name = "event_id") // Foreign key to events
    )
    private Set<Events> events = new HashSet<>();

   @Column(nullable = false)
    private int seatsBooked ;

   @Column(nullable = false)
    private String bookingStatus ;
}
