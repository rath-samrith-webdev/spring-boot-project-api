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
            utility.getCreatedAt()
        );
    }

    public Utility toEntity(UtilityDTO dto, User user) {
        Utility utility = new Utility();
        utility.setId(dto.id());
        utility.setUser(user);
        utility.setLabel(dto.label());
        utility.setDescription(dto.description());
        return utility;
    }
}
