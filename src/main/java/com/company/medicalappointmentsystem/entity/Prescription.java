package com.company.medicalappointmentsystem.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "PRESCRIPTION", indexes = {
        @Index(name = "IDX_PRESCRIPTION_DOCTOR", columnList = "DOCTOR_ID"),
        @Index(name = "IDX_PRESCRIPTION_ACCOUNT_PATIENT", columnList = "ACCOUNT_PATIENT_ID")
})
@Entity
public class Prescription {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @Column(name = "PAYMENT_STATUS")
    private Integer paymentStatus ;
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    @Composition
    private List<PrescriptionItem> items = new ArrayList<>();
    @Column(name = "PRESCRIPTION_DATE")
    private LocalDate prescriptionDate;
    @Column(name = "PATIENT_NAME")
    private String patientName;
    @Column(name = "NOTES")
    @Lob
    private String notes;
    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "ACCOUNT_PATIENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User accountPatient;
    @JoinColumn(name = "DOCTOR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    public PaymentStatus getPaymentStatus() {
        return paymentStatus == null ? null : PaymentStatus.fromId(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus == null ? null : paymentStatus.getId();
    }

    public void setAccountPatient(User accountPatient) {
        this.accountPatient = accountPatient;
    }

    public User getAccountPatient() {
        return accountPatient;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }
    public List<PrescriptionItem> getItems() {
        return items;
    }

    public void setItems(List<PrescriptionItem> items) {
        this.items = items;

    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }




    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    public String getDisplayName() {
        return this.id.toString();
    }
}