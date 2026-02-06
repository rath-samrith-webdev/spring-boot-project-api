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
    private final com.springdev.rentalApp.repositories.UserMonthlySavingRepository userMonthlySavingRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository,
                      com.springdev.rentalApp.repositories.PaymentStatusRepository paymentStatusRepository,
                      com.springdev.rentalApp.repositories.UserMonthlySavingRepository userMonthlySavingRepository,
                      org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.paymentStatusRepository = paymentStatusRepository;
        this.userMonthlySavingRepository = userMonthlySavingRepository;
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

        if (userMonthlySavingRepository.count() == 0 || forceSeed) {
            seedMonthlySavings();
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
            user1.setCurrentAddress("123 Main St, Anytown, USA");
            user1.setPhoneNumber("555-1234");
            user1.setPassword(passwordEncoder.encode("password123"));
            user1.setRole("ROLE_USER");
            user1.setIsActive(true);
            userRepository.save(user1);
        }

        if (userRepository.findByEmail("admin@java.com").isEmpty()) {
            User user2 = new User();
            user2.setFirstName("Admin");
            user2.setLastName("Smith");
            user2.setEmail("admin@java.com");
            user2.setCurrentAddress("456 Elm St, Othertown, USA");
            user2.setPhoneNumber("555-5678");
            user2.setPassword(passwordEncoder.encode("adminpassword"));
            user2.setRole("ROLE_ADMIN");
            user2.setIsActive(true);
            userRepository.save(user2);
        }
        System.out.println("Seeded Users.");
    }

    private void seedMonthlySavings() {
        if (userMonthlySavingRepository.count() > 0) {
            return;
        }

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            System.out.println("No users found. Skipping monthly savings seeding.");
            return;
        }

        java.util.List<com.springdev.rentalApp.entities.UserMonthlySaving> savings = new java.util.ArrayList<>();

        // Seed data for first user (consistent saver)
        if (users.size() > 0) {
            User user1 = users.get(0);
            double[] amounts2025 = {150.00, 175.50, 200.00, 180.25, 220.00, 195.75, 210.00, 185.50, 225.00, 240.00, 230.00, 250.00};
            for (int month = 1; month <= 12; month++) {
                var saving = new com.springdev.rentalApp.entities.UserMonthlySaving();
                saving.setUserId(user1.getId());
                saving.setYear(2025);
                saving.setMonth(month);
                saving.setAmount(amounts2025[month - 1]);
                saving.setStatus("completed");
                saving.setCreatedAt(String.format("2025-%02d-28T10:00:00", month));
                saving.setUpdatedAt(String.format("2025-%02d-28T10:00:00", month));
                savings.add(saving);
            }
            // 2026 data
            var saving2026_1 = new com.springdev.rentalApp.entities.UserMonthlySaving();
            saving2026_1.setUserId(user1.getId());
            saving2026_1.setYear(2026);
            saving2026_1.setMonth(1);
            saving2026_1.setAmount(260.00);
            saving2026_1.setStatus("completed");
            saving2026_1.setCreatedAt("2026-01-31T10:00:00");
            saving2026_1.setUpdatedAt("2026-01-31T10:00:00");
            savings.add(saving2026_1);

            var saving2026_2 = new com.springdev.rentalApp.entities.UserMonthlySaving();
            saving2026_2.setUserId(user1.getId());
            saving2026_2.setYear(2026);
            saving2026_2.setMonth(2);
            saving2026_2.setAmount(275.00);
            saving2026_2.setStatus("pending");
            saving2026_2.setCreatedAt("2026-02-01T10:00:00");
            saving2026_2.setUpdatedAt("2026-02-01T10:00:00");
            savings.add(saving2026_2);
        }

        // Seed data for second user (variable saver)
        if (users.size() > 1) {
            User user2 = users.get(1);
            double[] amounts2025 = {100.00, 120.00, 90.00, 150.00, 110.00, 130.00, 95.00, 140.00, 125.00, 160.00, 145.00, 180.00};
            for (int month = 1; month <= 12; month++) {
                var saving = new com.springdev.rentalApp.entities.UserMonthlySaving();
                saving.setUserId(user2.getId());
                saving.setYear(2025);
                saving.setMonth(month);
                saving.setAmount(amounts2025[month - 1]);
                saving.setStatus("completed");
                saving.setCreatedAt(String.format("2025-%02d-28T11:00:00", month));
                saving.setUpdatedAt(String.format("2025-%02d-28T11:00:00", month));
                savings.add(saving);
            }
            // 2026 data
            var saving2026_1 = new com.springdev.rentalApp.entities.UserMonthlySaving();
            saving2026_1.setUserId(user2.getId());
            saving2026_1.setYear(2026);
            saving2026_1.setMonth(1);
            saving2026_1.setAmount(170.00);
            saving2026_1.setStatus("completed");
            saving2026_1.setCreatedAt("2026-01-31T11:00:00");
            saving2026_1.setUpdatedAt("2026-01-31T11:00:00");
            savings.add(saving2026_1);

            var saving2026_2 = new com.springdev.rentalApp.entities.UserMonthlySaving();
            saving2026_2.setUserId(user2.getId());
            saving2026_2.setYear(2026);
            saving2026_2.setMonth(2);
            saving2026_2.setAmount(190.00);
            saving2026_2.setStatus("pending");
            saving2026_2.setCreatedAt("2026-02-01T11:00:00");
            saving2026_2.setUpdatedAt("2026-02-01T11:00:00");
            savings.add(saving2026_2);
        }

        userMonthlySavingRepository.saveAll(savings);
        System.out.println("Seeded " + savings.size() + " monthly savings records.");
    }
}
