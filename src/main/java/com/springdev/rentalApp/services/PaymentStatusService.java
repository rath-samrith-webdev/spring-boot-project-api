package com.springdev.rentalApp.services;

import java.util.List;
import com.springdev.rentalApp.dtos.PaymentStatusDTO;

public interface PaymentStatusService {
    List<PaymentStatusDTO> getAllStatuses();
}
