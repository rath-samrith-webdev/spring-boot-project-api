package com.springdev.rentalApp.services.Impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springdev.rentalApp.dtos.PaymentStatusDTO;
import com.springdev.rentalApp.mappers.PaymentStatusMapper;
import com.springdev.rentalApp.repositories.PaymentStatusRepository;
import com.springdev.rentalApp.services.PaymentStatusService;

@Service
public class PaymentStatusServiceImpl implements PaymentStatusService {

    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentStatusMapper paymentStatusMapper;

    public PaymentStatusServiceImpl(PaymentStatusRepository paymentStatusRepository, PaymentStatusMapper paymentStatusMapper) {
        this.paymentStatusRepository = paymentStatusRepository;
        this.paymentStatusMapper = paymentStatusMapper;
    }

    @Override
    public List<PaymentStatusDTO> getAllStatuses() {
        return paymentStatusRepository.findAll().stream()
                .map(paymentStatusMapper::toDto)
                .collect(Collectors.toList());
    }
}
