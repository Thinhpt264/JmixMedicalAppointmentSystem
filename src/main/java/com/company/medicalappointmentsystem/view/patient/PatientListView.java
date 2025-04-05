package com.company.medicalappointmentsystem.view.patient;

import com.company.medicalappointmentsystem.entity.Patient;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "patients", layout = MainView.class)
@ViewController(id = "Patient.list")
@ViewDescriptor(path = "patient-list-view.xml")
@LookupComponent("patientsDataGrid")
@DialogMode(width = "64em")
public class PatientListView extends StandardListView<Patient> {
}