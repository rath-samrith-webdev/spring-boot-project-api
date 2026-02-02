package com.springdev.rentalApp.services.Impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springdev.rentalApp.dtos.PaymentDTO;
import com.springdev.rentalApp.entities.Payment;
import com.springdev.rentalApp.entities.PaymentStatus;
import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.entities.Utility;
import com.springdev.rentalApp.mappers.PaymentMapper;
import com.springdev.rentalApp.repositories.PaymentRepository;
import com.springdev.rentalApp.repositories.PaymentStatusRepository;
import com.springdev.rentalApp.repositories.UserRepository;
import com.springdev.rentalApp.repositories.UtilityRepository;
import com.springdev.rentalApp.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UtilityRepository utilityRepository;
    private final UserRepository userRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UtilityRepository utilityRepository, UserRepository userRepository, PaymentStatusRepository paymentStatusRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.utilityRepository = utilityRepository;
        this.userRepository = userRepository;
        this.paymentStatusRepository = paymentStatusRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO dto) {
        Utility utility = utilityRepository.findById(dto.utilityId())
                .orElseThrow(() -> new RuntimeException("Utility not found"));

        User paidBy = userRepository.findById(dto.paidById())
                .orElseThrow(() -> new RuntimeException("Payer User not found"));

        PaymentStatus status = null;
        if (dto.paymentStatusId() != null) {
            status = paymentStatusRepository.findById(dto.paymentStatusId())
                    .orElseThrow(() -> new RuntimeException("Payment Status not found"));
        }

        User verifiedBy = null;
        if (dto.verifiedById() != null) {
            verifiedBy = userRepository.findById(dto.verifiedById())
                    .orElseThrow(() -> new RuntimeException("Verifier User not found"));
        }

        Payment payment = paymentMapper.toEntity(dto, utility, paidBy, status, verifiedBy);
        Payment saved = paymentRepository.save(payment);
        return paymentMapper.toDto(saved);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return paymentMapper.toDto(payment);
    }
}
