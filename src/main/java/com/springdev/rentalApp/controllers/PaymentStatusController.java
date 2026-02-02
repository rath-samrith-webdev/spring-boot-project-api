package com.springdev.rentalApp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdev.rentalApp.dtos.PaymentStatusDTO;
import com.springdev.rentalApp.services.PaymentStatusService;

@RestController
@RequestMapping("/api/payment-statuses")
public class PaymentStatusController {

    private final PaymentStatusService paymentStatusService;

    public PaymentStatusController(PaymentStatusService paymentStatusService) {
        this.paymentStatusService = paymentStatusService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentStatusDTO>> getAllStatuses() {
        return ResponseEntity.ok(paymentStatusService.getAllStatuses());
    }
}
