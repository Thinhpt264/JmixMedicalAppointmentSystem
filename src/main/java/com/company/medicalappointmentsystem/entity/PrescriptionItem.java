    package com.company.medicalappointmentsystem.entity;

    import io.jmix.core.DeletePolicy;
    import io.jmix.core.entity.annotation.JmixGeneratedValue;
    import io.jmix.core.entity.annotation.OnDelete;
    import io.jmix.core.entity.annotation.OnDeleteInverse;
    import io.jmix.core.metamodel.annotation.JmixEntity;
    import jakarta.persistence.*;

    import java.util.UUID;

    @JmixEntity
    @Table(name = "PRESCRIPTION_ITEM", indexes = {
            @Index(name = "IDX_PRESCRIPTION_ITEM_MEDICINE", columnList = "MEDICINE_ID"),
            @Index(name = "IDX_PRESCRIPTION_ITEM_PRESCRIPTION", columnList = "PRESCRIPTION_ID")
    })
    @Entity
    public class PrescriptionItem {
        @JmixGeneratedValue
        @Column(name = "ID", nullable = false)
        @Id
        private UUID id;
        @JoinColumn(name = "MEDICINE_ID")
        @ManyToOne(fetch = FetchType.LAZY)
        private Medicine medicine;
        @Column(name = "QUANTITY")
        private Integer quantity = 1;
        @Column(name = "NOTE")
        @Lob
        private String note;
        @OnDelete(DeletePolicy.CASCADE)
        @JoinColumn(name = "PRESCRIPTION_ID")
        @ManyToOne(fetch = FetchType.LAZY)
        private Prescription prescription;

        public Prescription getPrescription() {
            return prescription;
        }

        public void setPrescription(Prescription prescription) {
            this.prescription = prescription;

        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;

        }

        public Medicine getMedicine() {
            return medicine;
        }

        public void setMedicine(Medicine medicine) {
            this.medicine = medicine;

        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }



    }