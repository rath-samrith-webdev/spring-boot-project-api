package com.springdev.rentalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springdev.rentalApp.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
