package com.springdev.rentalApp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdev.rentalApp.dtos.ActivityDTO;
import com.springdev.rentalApp.services.ActivityService;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<ActivityDTO> logActivity(@RequestBody ActivityDTO dto) {
        return ResponseEntity.ok(activityService.logActivity(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ActivityDTO>> getUserActivities(@PathVariable Long userId) {
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }
}
