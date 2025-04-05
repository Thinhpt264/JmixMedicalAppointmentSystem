package com.company.medicalappointmentsystem.view.doctor;

import com.company.medicalappointmentsystem.entity.Doctor;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "doctors/:id", layout = MainView.class)
@ViewController(id = "Doctor.detail")
@ViewDescriptor(path = "doctor-detail-view.xml")
@EditedEntityContainer("doctorDc")
public class DoctorDetailView extends StandardDetailView<Doctor> {
}