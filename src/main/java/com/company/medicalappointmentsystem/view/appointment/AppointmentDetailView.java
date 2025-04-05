package com.company.medicalappointmentsystem.view.appointment;

import com.company.medicalappointmentsystem.entity.Appointment;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "appointments/:id", layout = MainView.class)
@ViewController(id = "Appointment.detail")
@ViewDescriptor(path = "appointment-detail-view.xml")
@EditedEntityContainer("appointmentDc")
public class AppointmentDetailView extends StandardDetailView<Appointment> {
}