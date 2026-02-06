package com.springdev.rentalApp.dtos;

public record UserSettingsDTO(
    Long id,
    Long userId,
    Integer reminderDaysBefore,
    Boolean emailNotifications,
    Boolean pushNotifications,
    String createdAt,
    String updatedAt
) {}
