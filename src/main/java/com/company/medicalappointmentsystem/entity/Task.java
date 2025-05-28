package com.company.medicalappointmentsystem.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@JmixEntity
@Table(name = "TASK_", indexes = {
        @Index(name = "IDX_TASK__EMPLOYEE", columnList = "EMPLOYEE_ID")
})
@Entity(name = "Task_")
public class Task {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @NotNull(message = "Employee Not Null")
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "EMPLOYEE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor employee;

    @NotNull
    @NotEmpty
    @InstanceName
    @Column(name = "NAME")
    private String name;
    @NotNull
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    @JoinColumn(name = "APPOINTMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Appointment appointment;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TYPE_")
    private String type;

    public void setEmployee(Doctor employee) {
        this.employee = employee;
    }

    public Doctor getEmployee() {
        return employee;
    }

    public TaskType getType() {
        return type == null ? null : TaskType.fromId(type);
    }

    public void setType(TaskType type) {
        this.type = type == null ? null : type.getId();
    }

    public TaskStatus getStatus() {
            return status == null ? null : TaskStatus.fromId(status);
        }

        public void setStatus(TaskStatus status) {
            this.status = status == null ? null : status.getId();
        }

        public Appointment getAppointment() {
            return appointment;
        }

        public void setAppointment(Appointment appointment) {
            this.appointment = appointment;
        }

        public LocalDateTime getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
        }

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

    @DependsOnProperties({"type"})
    @JmixProperty
    public String getTypeTextColor() {
        return Optional.ofNullable(getType())
                .map(TaskType::getTextColor)
                .orElse("");
    }

    @DependsOnProperties({"type"})
    @JmixProperty
    public String getTypeBorderColor() {
        return Optional.ofNullable(getType())
                .map(TaskType::getBorderColor)
                .orElse("");
    }

    @DependsOnProperties({"type"})
    @JmixProperty
    public String getTypeBackgroundColor() {
        return Optional.ofNullable(getType())
                .map(TaskType::getBackgroundColor)
                .orElse("");
    }
    @DependsOnProperties({"type", "employee", "name"})
    @JmixProperty
    public String getTitleName() {
        if (this.getType() == TaskType.ON_DUTY && this.getEmployee() != null) {
            return "Lịch Trực - " + this.getEmployee().getName();
        } else if (this.getType() == TaskType.SCHEDULED_CHECKUP && this.getAppointment() != null) {
            return "Lịch Hẹn: " + this.getAppointment().getPatient().getName();
        } else if (this.getType() == TaskType.ALL_DAY_EVENT) {
            return "Sự Kiện Cả Ngày: " + this.name;
        } else {
            return this.name != null ? this.name : "Công việc";
        }
    }

}