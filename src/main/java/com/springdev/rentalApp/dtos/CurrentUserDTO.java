package com.springdev.rentalApp.dtos;

import java.time.LocalDateTime;

public record CurrentUserDTO(
        Long id,
        String firstName,
        String lastName,
        String currentAddress,
        String phoneNumber,
        String email,
        String role,
        Boolean isActive,
        LocalDateTime lastLoginAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
