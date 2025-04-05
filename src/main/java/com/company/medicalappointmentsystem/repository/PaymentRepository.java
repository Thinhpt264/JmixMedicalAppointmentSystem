package com.company.medicalappointmentsystem.repository;

import com.company.medicalappointmentsystem.entity.Payment;
import io.jmix.core.repository.JmixDataRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JmixDataRepository<Payment, UUID> {

}