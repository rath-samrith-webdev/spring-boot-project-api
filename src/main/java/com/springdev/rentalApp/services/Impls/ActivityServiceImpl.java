package com.springdev.rentalApp.services.Impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springdev.rentalApp.dtos.ActivityDTO;
import com.springdev.rentalApp.entities.Activity;
import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.mappers.ActivityMapper;
import com.springdev.rentalApp.repositories.ActivityRepository;
import com.springdev.rentalApp.repositories.UserRepository;
import com.springdev.rentalApp.services.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ActivityMapper activityMapper;

    public ActivityServiceImpl(ActivityRepository activityRepository, UserRepository userRepository, ActivityMapper activityMapper) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.activityMapper = activityMapper;
    }

    @Override
    public ActivityDTO logActivity(ActivityDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Activity activity = activityMapper.toEntity(dto, user);
        Activity saved = activityRepository.save(activity);
        return activityMapper.toDto(saved);
    }

    @Override
    public List<ActivityDTO> getUserActivities(Long userId) {
        return activityRepository.findAll().stream()
                .filter(a -> a.getUser().getId().equals(userId))
                .map(activityMapper::toDto)
                .collect(Collectors.toList());
    }
}
