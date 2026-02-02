package com.springdev.rentalApp.services;

import java.util.List;
import com.springdev.rentalApp.dtos.NotificationDTO;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO notificationDto);
    List<NotificationDTO> getUserNotifications(Long userId);
    NotificationDTO markAsRead(Long id);
}
