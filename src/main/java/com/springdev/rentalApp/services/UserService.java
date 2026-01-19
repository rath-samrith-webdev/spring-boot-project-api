package com.springdev.rentalApp.services;

import java.util.List;

import com.springdev.rentalApp.dtos.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO UserDto);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDto);
    void deleteUser(Long id);
}
