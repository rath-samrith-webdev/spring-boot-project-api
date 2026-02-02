package com.springdev.rentalApp.dtos;

import java.time.LocalDateTime;

public record UtilityDTO(
    Long id,
    Long userId,
    String label,
    String description,
    LocalDateTime createdAt
) {}
