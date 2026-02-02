package com.springdev.rentalApp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdev.rentalApp.dtos.UtilityDTO;
import com.springdev.rentalApp.services.UtilityService;

@RestController
@RequestMapping("/api/utilities")
public class UtilityController {

    private final UtilityService utilityService;

    public UtilityController(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    @PostMapping
    public ResponseEntity<UtilityDTO> createUtility(@RequestBody UtilityDTO utilityDto) {
        return ResponseEntity.ok(utilityService.createUtility(utilityDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilityDTO> getUtilityById(@PathVariable Long id) {
        return ResponseEntity.ok(utilityService.getUtilityById(id));
    }

    @GetMapping
    public ResponseEntity<List<UtilityDTO>> getAllUtilities() {
        return ResponseEntity.ok(utilityService.getAllUtilities());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilityDTO> updateUtility(@PathVariable Long id, @RequestBody UtilityDTO utilityDto) {
        return ResponseEntity.ok(utilityService.updateUtility(id, utilityDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtility(@PathVariable Long id) {
        utilityService.deleteUtility(id);
        return ResponseEntity.noContent().build();
    }
}
