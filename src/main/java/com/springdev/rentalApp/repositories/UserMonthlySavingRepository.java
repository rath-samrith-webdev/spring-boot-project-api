package com.springdev.rentalApp.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springdev.rentalApp.entities.UserMonthlySaving;

public interface UserMonthlySavingRepository extends JpaRepository<UserMonthlySaving, Long> {
    List<UserMonthlySaving> findByUserId(Long userId);
    List<UserMonthlySaving> findByUserIdAndYear(Long userId, Integer year);
}
