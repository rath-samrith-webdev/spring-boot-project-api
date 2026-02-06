package com.springdev.rentalApp.mappers;

import org.springframework.stereotype.Component;
import com.springdev.rentalApp.dtos.UserSettingsDTO;
import com.springdev.rentalApp.entities.UserSettings;

@Component
public class UserSettingsMapper {
    public UserSettingsDTO toDto(UserSettings settings) {
        return new UserSettingsDTO(
            settings.getId(),
            settings.getUser().getId(),
            settings.getReminderDaysBefore(),
            settings.getEmailNotifications(),
            settings.getPushNotifications(),
            settings.getCreatedAt() != null ? settings.getCreatedAt().toString() : null,
            settings.getUpdatedAt() != null ? settings.getUpdatedAt().toString() : null
        );
    }

    public UserSettings toEntity(UserSettingsDTO dto, com.springdev.rentalApp.entities.User user) {
        UserSettings settings = new UserSettings();
        settings.setId(dto.id());
        settings.setUser(user);
        settings.setReminderDaysBefore(dto.reminderDaysBefore());
        settings.setEmailNotifications(dto.emailNotifications());
        settings.setPushNotifications(dto.pushNotifications());
        return settings;
    }
}
