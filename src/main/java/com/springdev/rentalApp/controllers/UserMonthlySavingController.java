package com.springdev.rentalApp.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springdev.rentalApp.dtos.UserMonthlySavingDTO;
import com.springdev.rentalApp.services.UserMonthlySavingService;

@RestController
@RequestMapping("/api/monthly-savings")
public class UserMonthlySavingController {

    private final UserMonthlySavingService service;

    public UserMonthlySavingController(UserMonthlySavingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserMonthlySavingDTO> createSaving(@RequestBody UserMonthlySavingDTO dto) {
        return ResponseEntity.ok(service.createSaving(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMonthlySavingDTO> getSavingById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSavingById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserMonthlySavingDTO>> getAllSavings() {
        return ResponseEntity.ok(service.getAllSavings());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserMonthlySavingDTO>> getSavingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getSavingsByUserId(userId));
    }

    @GetMapping("/user/{userId}/year/{year}")
    public ResponseEntity<List<UserMonthlySavingDTO>> getSavingsByUserIdAndYear(
            @PathVariable Long userId, @PathVariable Integer year) {
        return ResponseEntity.ok(service.getSavingsByUserIdAndYear(userId, year));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserMonthlySavingDTO> updateSaving(
            @PathVariable Long id, @RequestBody UserMonthlySavingDTO dto) {
        return ResponseEntity.ok(service.updateSaving(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaving(@PathVariable Long id) {
        service.deleteSaving(id);
        return ResponseEntity.noContent().build();
    }
}
