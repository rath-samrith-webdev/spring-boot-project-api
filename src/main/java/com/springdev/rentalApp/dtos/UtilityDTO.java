package com.springdev.rentalApp.dtos;

import java.time.LocalDateTime;

public record UtilityDTO(
    Long id,
    Long userId,
    String label,
    String description,
    String type,
    String provider,
    java.math.BigDecimal amount,
    java.time.LocalDateTime dueDate,
    String status,
    java.time.LocalDateTime createdAt
) {}
