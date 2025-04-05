package com.company.medicalappointmentsystem.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


    public enum TaskType implements EnumClass<String> {

    ON_DUTY("ON_DUTY", "event-blue", "STETHOSCOPE", "#D0F0FF", "#249FAF", "#000000"),
    SCHEDULED_CHECKUP("SCHEDULED_CHECKUP", "event-green", "CALENDAR", "#D6F5D6", "#23BA75", "#000000"),
    ALL_DAY_EVENT("ALL_DAY_EVENT", "event-purple", "SUN", "#EAD8FF", "#9C5CD1", "#000000");

    private final String id;
    private final String styleName;
    private final String icon;
    private final String backgroundColor;
    private final String borderColor;
    private final String textColor;

    TaskType(String value, String styleName, String icon, String backgroundColor, String borderColor, String textColor) {
        this.id = value;
        this.styleName = styleName;
        this.icon = icon;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.textColor = textColor;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TaskType fromId(String id) {
        for (TaskType at : TaskType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }

    public String getStyleName() {
        return styleName;
    }

    public String getIcon() {
        return icon;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public String getTextColor() {
        return textColor;
    }
}