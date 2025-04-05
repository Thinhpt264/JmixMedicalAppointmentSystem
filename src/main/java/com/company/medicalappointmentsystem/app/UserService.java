package com.company.medicalappointmentsystem.app;

import com.company.medicalappointmentsystem.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.security.impl.role.assignment.RoleAssignmentRepositoryImpl;
import io.jmix.security.role.assignment.RoleAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    private RoleAssignmentRepositoryImpl roleAssignmentRepository;


    public User getCurrentUser() {
        return (User) currentAuthentication.getUser();
    }

    public boolean hasRole(String roleCode) {
        String username = getCurrentUser().getUsername().toString();
        List<String> assignedRoles = roleAssignmentRepository.getAssignmentsByUsername(username)
                .stream()
                .map(RoleAssignment::getRoleCode)
                .collect(Collectors.toList());
        return assignedRoles.contains(roleCode);
    }

}