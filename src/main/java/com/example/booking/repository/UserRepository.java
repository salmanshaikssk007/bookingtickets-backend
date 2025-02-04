package com.example.booking.repository;

import com.example.booking.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
   
    Users findByEmail(String email);

    boolean existsByEmail(String email);
}
