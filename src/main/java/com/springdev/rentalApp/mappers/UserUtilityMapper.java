package com.springdev.rentalApp.mappers;

import org.springframework.stereotype.Component;

import com.springdev.rentalApp.dtos.UserUtilityDTO;
import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.entities.UserUtility;
import com.springdev.rentalApp.entities.UserUtilityId;
import com.springdev.rentalApp.entities.Utility;

@Component
public class UserUtilityMapper {
    public UserUtilityDTO toDto(UserUtility userUtility) {
        return new UserUtilityDTO(
            userUtility.getUser().getId(),
            userUtility.getUtility().getId(),
            userUtility.getRole()
        );
    }

    public UserUtility toEntity(UserUtilityDTO dto, User user, Utility utility) {
        UserUtility userUtility = new UserUtility();
        UserUtilityId id = new UserUtilityId(user.getId(), utility.getId());
        userUtility.setId(id);
        userUtility.setUser(user);
        userUtility.setUtility(utility);
        userUtility.setRole(dto.role());
        return userUtility;
    }
}
