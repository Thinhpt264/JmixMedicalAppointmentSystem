package com.company.medicalappointmentsystem.security;

import com.company.medicalappointmentsystem.entity.*;
import io.jmix.flowuidata.entity.FilterConfiguration;
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

    @MenuPolicy(menuIds = {"Appointment.list", "Prescription.list", "Payment.list"})
    @ViewPolicy(viewIds = {"Appointment.list", "Appointment.detail", "LoginView", "MainView", "User.detail", "Prescription.list", "Prescription.detail", "PrescriptionItem.detail", "Doctor.list", "Patient.detail", "resetPasswordView", "changePasswordView", "flowui_GroupFilterCondition.detail", "flowui_PropertyFilterCondition.detail", "flowui_AddConditionView", "flowui_JpqlFilterCondition.detail", "inputDialog", "Medicine.list", "Patient.list", "Payment.list"})
    void screens();

    @EntityAttributePolicy(entityClass = Appointment.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Appointment.class, actions = EntityPolicyAction.ALL)
    void appointment();

    @SpecificPolicy(resources = {"ui.loginToUi", "datatools.showEntityInfo"})
    void specific();

    @EntityAttributePolicy(entityClass = Prescription.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Prescription.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void prescription();

    @EntityAttributePolicy(entityClass = PrescriptionItem.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = PrescriptionItem.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void prescriptionItem();

    @EntityAttributePolicy(entityClass = Medicine.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Medicine.class, actions = EntityPolicyAction.READ)
    void medicine();

    @EntityAttributePolicy(entityClass = Payment.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Payment.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE, EntityPolicyAction.CREATE})
    void payment();

    @EntityAttributePolicy(entityClass = Doctor.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Doctor.class, actions = EntityPolicyAction.READ)
    void doctor();

    @EntityAttributePolicy(entityClass = Patient.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Patient.class, actions = EntityPolicyAction.READ)
    void patient();

    @EntityPolicy(entityClass = FilterConfiguration.class, actions = {
            EntityPolicyAction.READ,
            EntityPolicyAction.CREATE,
            EntityPolicyAction.UPDATE
    })
    void flowuiFilterAccess();
}