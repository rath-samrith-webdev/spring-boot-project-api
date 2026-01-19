package com.springdev.rentalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springdev.rentalApp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {}
