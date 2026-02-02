package com.springdev.rentalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springdev.rentalApp.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
