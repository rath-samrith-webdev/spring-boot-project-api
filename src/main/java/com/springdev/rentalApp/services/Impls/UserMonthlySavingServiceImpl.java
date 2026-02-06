package com.springdev.rentalApp.services.Impls;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.springdev.rentalApp.dtos.UserMonthlySavingDTO;
import com.springdev.rentalApp.entities.UserMonthlySaving;
import com.springdev.rentalApp.mappers.UserMonthlySavingMapper;
import com.springdev.rentalApp.repositories.UserMonthlySavingRepository;
import com.springdev.rentalApp.services.UserMonthlySavingService;

@Service
public class UserMonthlySavingServiceImpl implements UserMonthlySavingService {

    private final UserMonthlySavingRepository repository;
    private final UserMonthlySavingMapper mapper;

    public UserMonthlySavingServiceImpl(UserMonthlySavingRepository repository, UserMonthlySavingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserMonthlySavingDTO createSaving(UserMonthlySavingDTO dto) {
        UserMonthlySaving entity = mapper.toEntity(dto);
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(LocalDateTime.now().toString());
        }
        if (entity.getUpdatedAt() == null) {
            entity.setUpdatedAt(LocalDateTime.now().toString());
        }
        UserMonthlySaving saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public UserMonthlySavingDTO getSavingById(Long id) {
        UserMonthlySaving entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Monthly saving record not found"));
        return mapper.toDto(entity);
    }

    @Override
    public List<UserMonthlySavingDTO> getAllSavings() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserMonthlySavingDTO> getSavingsByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserMonthlySavingDTO> getSavingsByUserIdAndYear(Long userId, Integer year) {
        return repository.findByUserIdAndYear(userId, year).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserMonthlySavingDTO updateSaving(Long id, UserMonthlySavingDTO dto) {
        UserMonthlySaving entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Monthly saving record not found"));

        entity.setUserId(dto.userId());
        entity.setYear(dto.year());
        entity.setMonth(dto.month());
        entity.setAmount(dto.amount());
        entity.setStatus(dto.status());
        entity.setUpdatedAt(LocalDateTime.now().toString());

        UserMonthlySaving updated = repository.save(entity);
        return mapper.toDto(updated);
    }

    @Override
    public void deleteSaving(Long id) {
        repository.deleteById(id);
    }
}
