package com.company.medicalappointmentsystem.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum Gender implements EnumClass<Integer> {

    MAN(1),
    HUMAN(2);

    private final Integer id;

    Gender(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static Gender fromId(Integer id) {
        for (Gender at : Gender.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}