package com.company.medicalappointmentsystem.security;

import com.company.medicalappointmentsystem.entity.Appointment;
import com.company.medicalappointmentsystem.entity.Prescription;
import com.company.medicalappointmentsystem.entity.PrescriptionItem;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "PatientRole", code = PatientRole.CODE)
public interface PatientRole {
    String CODE = "patient-role";

    @MenuPolicy(menuIds = {"Appointment.list", "Prescription.list"})
    @ViewPolicy(viewIds = {"Appointment.list", "Appointment.detail", "LoginView", "MainView", "User.detail", "Prescription.list", "Prescription.detail", "PrescriptionItem.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = Appointment.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Appointment.class, actions = EntityPolicyAction.READ)
    void appointment();

    @SpecificPolicy(resources = {"ui.loginToUi", "datatools.showEntityInfo"})
    void specific();

    @EntityAttributePolicy(entityClass = Prescription.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Prescription.class, actions = EntityPolicyAction.READ)
    void prescription();

    @EntityAttributePolicy(entityClass = PrescriptionItem.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PrescriptionItem.class, actions = EntityPolicyAction.READ)
    void prescriptionItem();
}