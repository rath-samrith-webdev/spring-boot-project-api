package com.springdev.rentalApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springdev.rentalApp.entities.UserUtility;
import com.springdev.rentalApp.entities.UserUtilityId;

public interface UserUtilityRepository extends JpaRepository<UserUtility, UserUtilityId> {
}
