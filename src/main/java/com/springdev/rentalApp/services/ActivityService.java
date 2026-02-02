package com.springdev.rentalApp.services;

import java.util.List;
import com.springdev.rentalApp.dtos.ActivityDTO;

public interface ActivityService {
    ActivityDTO logActivity(ActivityDTO activityDto);
    List<ActivityDTO> getUserActivities(Long userId);
}
