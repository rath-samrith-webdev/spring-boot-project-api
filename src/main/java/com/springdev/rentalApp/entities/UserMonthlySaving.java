package com.springdev.rentalApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_monthly_savings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMonthlySaving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Integer year;
    private Integer month;
    private Double amount;
    private String status;
    private String createdAt;
    private String updatedAt;
}
