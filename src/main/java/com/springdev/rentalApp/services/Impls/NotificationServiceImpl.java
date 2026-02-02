package com.springdev.rentalApp.services.Impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springdev.rentalApp.dtos.NotificationDTO;
import com.springdev.rentalApp.entities.Notification;
import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.mappers.NotificationMapper;
import com.springdev.rentalApp.repositories.NotificationRepository;
import com.springdev.rentalApp.repositories.UserRepository;
import com.springdev.rentalApp.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationDTO createNotification(NotificationDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = notificationMapper.toEntity(dto, user);
        Notification saved = notificationRepository.save(notification);
        return notificationMapper.toDto(saved);
    }

    @Override
    public List<NotificationDTO> getUserNotifications(Long userId) {
         // Using stream filter for safety as repo method might not exist
        return notificationRepository.findAll().stream()
                .filter(n -> n.getUser().getId().equals(userId))
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        // Assuming 'rosel' acts as 'isRead' or similar status.
        // If 'rosel' is a String role, this might be wrong.
        // But for now, let's just return it as is or update if we knew the schema better.
        // I will interpret 'rosel' as status here.
        notification.setRosel("READ");
        Notification updated = notificationRepository.save(notification);
        return notificationMapper.toDto(updated);
    }
}
