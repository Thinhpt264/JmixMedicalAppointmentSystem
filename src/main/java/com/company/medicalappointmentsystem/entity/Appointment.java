package com.company.medicalappointmentsystem.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "APPOINTMENT", indexes = {
        @Index(name = "IDX_APPOINTMENT_DOCTOR", columnList = "DOCTOR_ID")
})
@Entity
public class Appointment {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @Column(name = "REASON")
    private String reason;
    @NotNull(message = "requiment")
    @Column(name = "DATE_")
    private LocalDateTime date;
    @Column(name = "STATUS")
    private Integer status;
    @NotNull(message = "requiment")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;
    @NotNull(message = "requiment")
    @JoinColumn(name = "DOCTOR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Status getStatus() {
        return status == null ? null : Status.fromId(status);
    }

    public void setStatus(Status status) {
        this.status = status == null ? null : status.getId();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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