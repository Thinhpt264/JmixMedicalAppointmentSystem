package com.company.medicalappointmentsystem.app;

import com.company.medicalappointmentsystem.entity.Doctor;
import com.company.medicalappointmentsystem.entity.Prescription;
import com.company.medicalappointmentsystem.entity.User;
import com.company.medicalappointmentsystem.repository.DoctorRepository;
import com.company.medicalappointmentsystem.repository.PrescriptionRepository;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Prescription> getPrescriptionsForUser() {
        User user = (User) currentAuthentication.getUser();
        System.out.println(prescriptionRepository.findPrescriptionByAccountPatient(user));
        return prescriptionRepository.findPrescriptionByAccountPatient(user);
    }

    public List<Prescription> getPrescriptionsForDoctor() {
        User user = (User) currentAuthentication.getUser();
        Optional<Doctor> doctor = doctorRepository.findByAccount(user);

        return doctor.map(doctor1 ->
                prescriptionRepository.findPrescriptionByDoctor(doctor1)
        ).orElse(Collections.emptyList());
    }



}