package com.springdev.rentalApp.mappers;

import org.springframework.stereotype.Component;

import com.springdev.rentalApp.dtos.NotificationDTO;
import com.springdev.rentalApp.entities.Notification;
import com.springdev.rentalApp.entities.User;

@Component
public class NotificationMapper {
    public NotificationDTO toDto(Notification notification) {
        return new NotificationDTO(
            notification.getId(),
            notification.getUser().getId(),
            notification.getMessage(),
            notification.getRosel(),
            notification.getCreatedAt()
        );
    }

    public Notification toEntity(NotificationDTO dto, User user) {
        Notification notification = new Notification();
        notification.setId(dto.id());
        notification.setUser(user);
        notification.setMessage(dto.message());
        notification.setRosel(dto.rosel());
        return notification;
    }
}
