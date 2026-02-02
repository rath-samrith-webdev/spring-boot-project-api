package com.springdev.rentalApp.dtos;

import java.time.LocalDateTime;

public record NotificationDTO(
    Long id,
    Long userId,
    String message,
    String rosel,
    LocalDateTime createdAt
) {}
