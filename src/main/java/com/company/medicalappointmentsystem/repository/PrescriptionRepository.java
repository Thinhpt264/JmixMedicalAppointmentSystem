package com.company.medicalappointmentsystem.repository;

import com.company.medicalappointmentsystem.entity.Doctor;
import com.company.medicalappointmentsystem.entity.Prescription;
import com.company.medicalappointmentsystem.entity.User;
import io.jmix.core.repository.JmixDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrescriptionRepository extends JmixDataRepository<Prescription, UUID> {

    List<Prescription> findPrescriptionByAccountPatient(User accountPatient);

    List<Prescription> findPrescriptionByDoctor(Doctor doctor);

}