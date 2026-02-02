package com.springdev.rentalApp.dtos;

import java.time.LocalDateTime;

public record ActivityDTO(
    Long id,
    Long userId,
    String action,
    String entityType,
    Long entityId,
    LocalDateTime createdAt
) {}
