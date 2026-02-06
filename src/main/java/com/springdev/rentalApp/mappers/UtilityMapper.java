package com.springdev.rentalApp.mappers;

import org.springframework.stereotype.Component;

import com.springdev.rentalApp.dtos.UtilityDTO;
import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.entities.Utility;

@Component
public class UtilityMapper {
    public UtilityDTO toDto(Utility utility) {
        return new UtilityDTO(
            utility.getId(),
            utility.getUser().getId(),
            utility.getLabel(),
            utility.getDescription(),
            utility.getType(),
            utility.getProvider(),
            utility.getAmount(),
            utility.getDueDate(),
            utility.getStatus(),
            utility.getCreatedAt()
        );
    }

    public Utility toEntity(UtilityDTO dto, User user) {
        Utility utility = new Utility();
        utility.setId(dto.id());
        utility.setUser(user);
        utility.setLabel(dto.label());
        utility.setDescription(dto.description());
        utility.setType(dto.type());
        utility.setProvider(dto.provider());
        utility.setAmount(dto.amount());
        utility.setDueDate(dto.dueDate());
        utility.setStatus(dto.status());
        return utility;
    }
}
