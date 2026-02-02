package com.springdev.rentalApp.services.Impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springdev.rentalApp.dtos.UtilityDTO;
import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.entities.Utility;
import com.springdev.rentalApp.mappers.UtilityMapper;
import com.springdev.rentalApp.repositories.UserRepository;
import com.springdev.rentalApp.repositories.UtilityRepository;
import com.springdev.rentalApp.services.UtilityService;

@Service
public class UtilityServiceImpl implements UtilityService {

    private final UtilityRepository utilityRepository;
    private final UserRepository userRepository;
    private final UtilityMapper utilityMapper;

    public UtilityServiceImpl(UtilityRepository utilityRepository, UserRepository userRepository, UtilityMapper utilityMapper) {
        this.utilityRepository = utilityRepository;
        this.userRepository = userRepository;
        this.utilityMapper = utilityMapper;
    }

    @Override
    public UtilityDTO createUtility(UtilityDTO utilityDto) {
        User user = userRepository.findById(utilityDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Utility utility = utilityMapper.toEntity(utilityDto, user);
        Utility savedUtility = utilityRepository.save(utility);
        return utilityMapper.toDto(savedUtility);
    }

    @Override
    public UtilityDTO getUtilityById(Long id) {
        Utility utility = utilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utility not found"));
        return utilityMapper.toDto(utility);
    }

    @Override
    public List<UtilityDTO> getAllUtilities() {
        return utilityRepository.findAll().stream()
                .map(utilityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UtilityDTO updateUtility(Long id, UtilityDTO utilityDto) {
        Utility utility = utilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utility not found"));

        utility.setLabel(utilityDto.label());
        utility.setDescription(utilityDto.description());

        Utility updatedUtility = utilityRepository.save(utility);
        return utilityMapper.toDto(updatedUtility);
    }

    @Override
    public void deleteUtility(Long id) {
        utilityRepository.deleteById(id);
    }
}
