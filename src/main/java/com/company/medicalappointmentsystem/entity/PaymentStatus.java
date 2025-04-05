package com.company.medicalappointmentsystem.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum PaymentStatus implements EnumClass<Integer> {

    UNPAID(1),
    PAID(3),
    PENDING(2),
    FAILED(4);

    private final Integer id;

    PaymentStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static PaymentStatus fromId(Integer id) {
        for (PaymentStatus at : PaymentStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}