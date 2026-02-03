package com.springdev.rentalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springdev.rentalApp.entities.RevokedToken;

public interface RevokedTokenRepository extends JpaRepository<RevokedToken, Long> {
    boolean existsByTokenHash(String tokenHash);
}
