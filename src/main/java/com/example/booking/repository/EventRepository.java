package com.example.booking.repository;

import com.example.booking.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events , Long> {
}
