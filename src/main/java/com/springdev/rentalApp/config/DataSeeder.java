package com.springdev.rentalApp.config;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.repositories.UserRepository;

@Configuration
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final com.springdev.rentalApp.repositories.PaymentStatusRepository paymentStatusRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, com.springdev.rentalApp.repositories.PaymentStatusRepository paymentStatusRepository, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.paymentStatusRepository = paymentStatusRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean forceSeed = false;
        for (String arg : args) {
            if ("--seed".equals(arg)) {
                forceSeed = true;
                break;
            }
        }

        if (paymentStatusRepository.count() == 0 || forceSeed) {
            seedPaymentStatuses();
        }

        if (userRepository.count() == 0 || forceSeed) {
            seedUsers();
        }
    }

    private void seedPaymentStatuses() {
        if (paymentStatusRepository.count() == 0) {
            var s1 = new com.springdev.rentalApp.entities.PaymentStatus(); s1.setLabel("PENDING");
            var s2 = new com.springdev.rentalApp.entities.PaymentStatus(); s2.setLabel("PAID");
            var s3 = new com.springdev.rentalApp.entities.PaymentStatus(); s3.setLabel("FAILED");
            paymentStatusRepository.saveAll(List.of(s1, s2, s3));
            System.out.println("Seeded PaymentStatuses.");
        }
    }

    private void seedUsers() {
        if (userRepository.findByEmail("john.doe@example.com").isEmpty()) {
            User user1 = new User();
            user1.setFirstName("John");
            user1.setLastName("Doe");
            user1.setEmail("john.doe@example.com");
            user1.setPassword(passwordEncoder.encode("password123"));
            user1.setRole("ROLE_USER");
            user1.setIsActive(true);
            userRepository.save(user1);
        }

        if (userRepository.findByEmail("jane.smith@example.com").isEmpty()) {
            User user2 = new User();
            user2.setFirstName("Jane");
            user2.setLastName("Smith");
            user2.setEmail("jane.smith@example.com");
            user2.setPassword(passwordEncoder.encode("password123"));
            user2.setRole("ROLE_ADMIN");
            user2.setIsActive(true);
            userRepository.save(user2);
        }
        System.out.println("Seeded Users.");
    }
}
