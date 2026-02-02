package com.springdev.rentalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springdev.rentalApp.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
