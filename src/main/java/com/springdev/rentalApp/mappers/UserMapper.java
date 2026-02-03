package com.springdev.rentalApp.mappers;

import org.springframework.stereotype.Component;

import com.springdev.rentalApp.dtos.CurrentUserDTO;
import com.springdev.rentalApp.dtos.UserDTO;
import com.springdev.rentalApp.entities.User;

@Component
public class UserMapper {
    public UserDTO toDto(User user) {
        return new UserDTO(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getCurrentAddress(),
            user.getPhoneNumber(),
            user.getEmail()
        );
    }

    public CurrentUserDTO toCurrentUserDto(User user) {
        return new CurrentUserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCurrentAddress(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRole(),
                user.getIsActive(),
                user.getLastLoginAt(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public User toEntity(UserDTO userDto) {
        User user = new User();
        user.setId(userDto.id());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setCurrentAddress(userDto.currentAddress());
        user.setPhoneNumber(userDto.phoneNumber());
        user.setEmail(userDto.email());
        return user;
    }
}
