package com.company.medicalappointmentsystem.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "PAYMENT", indexes = {
        @Index(name = "IDX_PAYMENT_PRESCRIPTION", columnList = "PRESCRIPTION_ID")
})
@Entity
public class Payment {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PRESCRIPTION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @InstanceName
    private Prescription prescription;
    @Column(name = "TRANSACTION_ID")
    private String transactionId;
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "METHOD_")
    private String method;
    @Column(name = "PAYMENT_TIME")
    private LocalDateTime paymentTime;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}