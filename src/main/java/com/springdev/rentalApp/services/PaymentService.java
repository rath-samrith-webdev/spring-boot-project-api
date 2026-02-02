package com.springdev.rentalApp.services;

import java.util.List;
import com.springdev.rentalApp.dtos.PaymentDTO;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDto);
    List<PaymentDTO> getAllPayments();
    PaymentDTO getPaymentById(Long id);
}
