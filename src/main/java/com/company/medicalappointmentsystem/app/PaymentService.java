package com.company.medicalappointmentsystem.app;

import com.company.medicalappointmentsystem.entity.Payment;
import com.company.medicalappointmentsystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;


    public boolean savePayment(Payment payment) {
        if(paymentRepository.save(payment) != null) {
            return true;
        } else {
            return false ;
        }
    };

}