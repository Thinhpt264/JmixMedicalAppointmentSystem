package com.company.medicalappointmentsystem.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum TaskStatus implements EnumClass<String> {

    UPCOMING("UPCOMING"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    private final String id;

    TaskStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TaskStatus fromId(String id) {
        for (TaskStatus at : TaskStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}