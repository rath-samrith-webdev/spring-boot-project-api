package com.springdev.rentalApp.mappers;

import org.springframework.stereotype.Component;
import com.springdev.rentalApp.dtos.UserMonthlySavingDTO;
import com.springdev.rentalApp.entities.UserMonthlySaving;

@Component
public class UserMonthlySavingMapper {
    public UserMonthlySavingDTO toDto(UserMonthlySaving entity) {
        return new UserMonthlySavingDTO(
            entity.getId(),
            entity.getUserId(),
            entity.getYear(),
            entity.getMonth(),
            entity.getAmount(),
            entity.getStatus(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    public UserMonthlySaving toEntity(UserMonthlySavingDTO dto) {
        UserMonthlySaving entity = new UserMonthlySaving();
        entity.setId(dto.id());
        entity.setUserId(dto.userId());
        entity.setYear(dto.year());
        entity.setMonth(dto.month());
        entity.setAmount(dto.amount());
        entity.setStatus(dto.status());
        entity.setCreatedAt(dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt());
        return entity;
    }
}
