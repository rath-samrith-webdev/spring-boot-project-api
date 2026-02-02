package com.springdev.rentalApp.services;

import java.util.List;
import com.springdev.rentalApp.dtos.UtilityDTO;

public interface UtilityService {
    UtilityDTO createUtility(UtilityDTO utilityDto);
    UtilityDTO getUtilityById(Long id);
    List<UtilityDTO> getAllUtilities();
    UtilityDTO updateUtility(Long id, UtilityDTO utilityDto);
    void deleteUtility(Long id);
}
