package com.springdev.rentalApp.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.repositories.UserRepository;

@Configuration
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setFirstName("John");
            user1.setLastName("Doe");
            user1.setEmail("john.doe@example.com");
            user1.setDateOfBirth(LocalDate.of(1990, 1, 1));
            user1.setPassword(passwordEncoder.encode("password123"));
            user1.setRole("ROLE_USER");

            User user2 = new User();
            user2.setFirstName("Jane");
            user2.setLastName("Smith");
            user2.setEmail("jane.smith@example.com");
            user2.setDateOfBirth(LocalDate.of(1995, 5, 15));
            user2.setPassword(passwordEncoder.encode("password123"));
            user2.setRole("ROLE_ADMIN");

            userRepository.saveAll(List.of(user1, user2));
            System.out.println("Database seeded with initial users.");
        } else {
            System.out.println("Database already contains users. Skipping seeding.");
        }
    }
}
