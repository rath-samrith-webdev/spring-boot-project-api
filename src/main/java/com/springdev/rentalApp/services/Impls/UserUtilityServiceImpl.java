package com.springdev.rentalApp.services.Impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springdev.rentalApp.dtos.UserUtilityDTO;
import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.entities.UserUtility;
import com.springdev.rentalApp.entities.UserUtilityId;
import com.springdev.rentalApp.entities.Utility;
import com.springdev.rentalApp.mappers.UserUtilityMapper;
import com.springdev.rentalApp.repositories.UserRepository;
import com.springdev.rentalApp.repositories.UserUtilityRepository;
import com.springdev.rentalApp.repositories.UtilityRepository;
import com.springdev.rentalApp.services.UserUtilityService;

@Service
public class UserUtilityServiceImpl implements UserUtilityService {

    private final UserUtilityRepository userUtilityRepository;
    private final UserRepository userRepository;
    private final UtilityRepository utilityRepository;
    private final UserUtilityMapper userUtilityMapper;

    public UserUtilityServiceImpl(UserUtilityRepository userUtilityRepository, UserRepository userRepository, UtilityRepository utilityRepository, UserUtilityMapper userUtilityMapper) {
        this.userUtilityRepository = userUtilityRepository;
        this.userRepository = userRepository;
        this.utilityRepository = utilityRepository;
        this.userUtilityMapper = userUtilityMapper;
    }

    @Override
    public UserUtilityDTO assignUtilityToUser(UserUtilityDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Utility utility = utilityRepository.findById(dto.utilityId())
                .orElseThrow(() -> new RuntimeException("Utility not found"));

        UserUtility userUtility = userUtilityMapper.toEntity(dto, user, utility);
        UserUtility saved = userUtilityRepository.save(userUtility);
        return userUtilityMapper.toDto(saved);
    }

    @Override
    public List<UserUtilityDTO> getUserUtilities(Long userId) {
        // Assuming we might need a custom query in Repository to find by userId, or just findAll and filter (inefficient but okay for now if repo doesn't support it yet)
        // Ideally UserUtilityRepository should have findByUserId(Long userId)
        // Checking UserUtilityRepository... it extends JpaRepository<UserUtility, UserUtilityId>
        // It likely doesn't have findByUser_Id yet.
        // For now, I will assume it exists or I will need to iterate.
        // Actually, to succeed compilation, I should check the repo.
        // Just in case, I will use findAll() and stream filter for now to avoid compilation error if method missing.
        // Better: I will use findAll and filter.
        return userUtilityRepository.findAll().stream()
                .filter(uu -> uu.getId().getUserId().equals(userId))
                .map(userUtilityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeUtilityFromUser(Long userId, Long utilityId) {
        UserUtilityId id = new UserUtilityId(userId, utilityId);
        userUtilityRepository.deleteById(id);
    }
}
