package com.company.medicalappointmentsystem.app;

import com.company.medicalappointmentsystem.entity.Prescription;
import com.company.medicalappointmentsystem.entity.User;
import com.company.medicalappointmentsystem.repository.PrescriptionRepository;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    public List<Prescription> getPrescriptionsForUser() {
        User user = (User) currentAuthentication.getUser(); // Lấy User hiện tại
        return prescriptionRepository.findPrescriptionByAccountPatient(user);
    }

}