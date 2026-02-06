package com.springdev.rentalApp.services;

import java.util.List;
import com.springdev.rentalApp.dtos.UserMonthlySavingDTO;

public interface UserMonthlySavingService {
    UserMonthlySavingDTO createSaving(UserMonthlySavingDTO dto);
    UserMonthlySavingDTO getSavingById(Long id);
    List<UserMonthlySavingDTO> getAllSavings();
    List<UserMonthlySavingDTO> getSavingsByUserId(Long userId);
    List<UserMonthlySavingDTO> getSavingsByUserIdAndYear(Long userId, Integer year);
    UserMonthlySavingDTO updateSaving(Long id, UserMonthlySavingDTO dto);
    void deleteSaving(Long id);
}
