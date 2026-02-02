package com.springdev.rentalApp.services;

import java.util.List;
import com.springdev.rentalApp.dtos.UserUtilityDTO;

public interface UserUtilityService {
    UserUtilityDTO assignUtilityToUser(UserUtilityDTO userUtilityDto);
    List<UserUtilityDTO> getUserUtilities(Long userId);
    void removeUtilityFromUser(Long userId, Long utilityId);
}
