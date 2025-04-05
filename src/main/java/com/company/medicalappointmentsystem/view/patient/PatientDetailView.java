package com.company.medicalappointmentsystem.view.patient;

import com.company.medicalappointmentsystem.entity.Patient;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "patients/:id", layout = MainView.class)
@ViewController(id = "Patient.detail")
@ViewDescriptor(path = "patient-detail-view.xml")
@EditedEntityContainer("patientDc")
public class PatientDetailView extends StandardDetailView<Patient> {
}