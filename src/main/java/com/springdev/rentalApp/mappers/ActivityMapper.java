package com.springdev.rentalApp.mappers;

import org.springframework.stereotype.Component;

import com.springdev.rentalApp.dtos.ActivityDTO;
import com.springdev.rentalApp.entities.Activity;
import com.springdev.rentalApp.entities.User;

@Component
public class ActivityMapper {
    public ActivityDTO toDto(Activity activity) {
        return new ActivityDTO(
            activity.getId(),
            activity.getUser().getId(),
            activity.getAction(),
            activity.getEntityType(),
            activity.getEntityId(),
            activity.getCreatedAt()
        );
    }

    public Activity toEntity(ActivityDTO dto, User user) {
        Activity activity = new Activity();
        activity.setId(dto.id());
        activity.setUser(user);
        activity.setAction(dto.action());
        activity.setEntityType(dto.entityType());
        activity.setEntityId(dto.entityId());
        return activity;
    }
}
