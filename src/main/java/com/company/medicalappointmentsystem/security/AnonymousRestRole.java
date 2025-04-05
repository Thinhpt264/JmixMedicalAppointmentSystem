package com.company.medicalappointmentsystem.security;

import com.company.medicalappointmentsystem.entity.Appointment;
import com.company.medicalappointmentsystem.entity.Doctor;
import com.company.medicalappointmentsystem.entity.Patient;
import com.company.medicalappointmentsystem.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "AnonymousRestRole", code = AnonymousRestRole.CODE, scope = "API")
public interface AnonymousRestRole {


    String CODE = "anonymous-rest-role";

    @EntityAttributePolicy(entityClass = Appointment.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Appointment.class, actions = EntityPolicyAction.ALL)
    void appointment();

    @EntityAttributePolicy(entityClass = Doctor.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Doctor.class, actions = EntityPolicyAction.ALL)
    void doctor();

    @MenuPolicy(menuIds = {"User.list", "Patient.list", "Appointment.list", "Doctor.list"})
    @ViewPolicy(viewIds = {"entityInfoView", "User.list", "User.detail", "Patient.list", "Appointment.list", "Doctor.list", "Doctor.detail", "Appointment.detail"})
    void screens();

    @SpecificPolicy(resources = {"datatools.showEntityInfo", "rest.enabled"})
    void specific();

    @EntityAttributePolicy(entityClass = Patient.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Patient.class, actions = EntityPolicyAction.ALL)
    void patient();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();
}