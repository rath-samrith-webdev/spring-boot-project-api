package com.springdev.rentalApp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdev.rentalApp.dtos.UserUtilityDTO;
import com.springdev.rentalApp.services.UserUtilityService;

@RestController
@RequestMapping("/api/user-utilities")
public class UserUtilityController {

    private final UserUtilityService userUtilityService;

    public UserUtilityController(UserUtilityService userUtilityService) {
        this.userUtilityService = userUtilityService;
    }

    @PostMapping
    public ResponseEntity<UserUtilityDTO> assignUtilityToUser(@RequestBody UserUtilityDTO dto) {
        return ResponseEntity.ok(userUtilityService.assignUtilityToUser(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserUtilityDTO>> getUserUtilities(@PathVariable Long userId) {
        return ResponseEntity.ok(userUtilityService.getUserUtilities(userId));
    }

    @DeleteMapping("/user/{userId}/utility/{utilityId}")
    public ResponseEntity<Void> removeUtilityFromUser(@PathVariable Long userId, @PathVariable Long utilityId) {
        userUtilityService.removeUtilityFromUser(userId, utilityId);
        return ResponseEntity.noContent().build();
    }
}
