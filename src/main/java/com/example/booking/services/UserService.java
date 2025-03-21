package com.example.booking.services;

import com.example.booking.model.Users;
import com.example.booking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    Save a new user
    public Users saveUser(Users user){
        // Encode the user password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    // Find a user by email
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    // Get user by ID
    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }
    // Check if a user exists by email
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    // List all users
    public Iterable<Users> getAllUsers() {
        return userRepository.findAll();
    }
    // Update user details (e.g., name, roles)
    public Users updateUser(Users user) {
        Optional<Users> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            Users updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setRole(user.getRole());
            return userRepository.save(updatedUser);
        }
        throw new RuntimeException("User not found with ID: " + user.getId());
    }
    // Delete a user by ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
