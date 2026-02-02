package com.springdev.rentalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springdev.rentalApp.entities.PaymentStatus;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
}
