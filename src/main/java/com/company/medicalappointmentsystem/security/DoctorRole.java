package com.company.medicalappointmentsystem.security;

import com.company.medicalappointmentsystem.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "DoctorRole", code = DoctorRole.CODE)
public interface DoctorRole {
    String CODE = "doctor-role";

    @MenuPolicy(menuIds = {"Appointment.list", "Patient.list", "Medicine.list", "Prescription.list", "MyCalendar", "Task_.list", "Payment.list"})
    @ViewPolicy(viewIds = {"Appointment.list", "Appointment.detail", "Patient.list", "LoginView", "MainView", "Medicine.list", "Prescription.list", "MyCalendar", "Task_.list", "Prescription.detail", "PrescriptionItem.detail", "Task_.detail", "Payment.list", "Payment.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = Appointment.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Appointment.class, actions = EntityPolicyAction.ALL)
    void appointment();

    @EntityAttributePolicy(entityClass = Patient.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Patient.class, actions = EntityPolicyAction.ALL)
    void patient();

    @SpecificPolicy(resources = {"ui.loginToUi", "datatools.showEntityInfo", "ui.showExceptionDetails"})
    void specific();

    @EntityAttributePolicy(entityClass = Task.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.ALL)
    void task();

    @EntityAttributePolicy(entityClass = PrescriptionItem.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = PrescriptionItem.class, actions = EntityPolicyAction.ALL)
    void prescriptionItem();

    @EntityAttributePolicy(entityClass = Prescription.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Prescription.class, actions = EntityPolicyAction.ALL)
    void prescription();

    @EntityAttributePolicy(entityClass = Medicine.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Medicine.class, actions = EntityPolicyAction.ALL)
    void medicine();

    @EntityAttributePolicy(entityClass = Payment.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Payment.class, actions = EntityPolicyAction.READ)
    void payment();
}