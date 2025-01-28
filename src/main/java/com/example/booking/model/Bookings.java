package com.example.booking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Data
public class Bookings implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "userId" , nullable = false)
    private Users user ;

    @ManyToMany
    @JoinColumn(name = "eventId" , nullable = false)
    private Events event;

   @Column(nullable = false)
    private int seatsBooked ;

   @Column(nullable = false)
    private String bookingStatus ;
}
