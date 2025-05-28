package com.company.medicalappointmentsystem.view.appointment;

import com.company.medicalappointmentsystem.app.UserService;
import com.company.medicalappointmentsystem.entity.Appointment;
import com.company.medicalappointmentsystem.entity.Patient;
import com.company.medicalappointmentsystem.entity.Status;
import com.company.medicalappointmentsystem.entity.User;
import com.company.medicalappointmentsystem.repository.PatientRepository;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Route(value = "appointments/:id", layout = MainView.class)
@ViewController(id = "Appointment.detail")
@ViewDescriptor(path = "appointment-detail-view.xml")
@EditedEntityContainer("appointmentDc")
public class AppointmentDetailView extends StandardDetailView<Appointment> {
    @ViewComponent
    private Select<String> statusField;

    @Autowired
    private UserService userService;

    @ViewComponent
    private EntityPicker<Patient> patientField;
    @Autowired
    private PatientRepository patientRepository;


    @Subscribe
    public void onInitEntity(final InitEntityEvent<Appointment> event) {
        // Gán trạng thái mặc định nếu là entity mới
        event.getEntity().setStatus(Status.PENDING);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        // Make status field read-only if the entity is not new
        if (!isNewEntity()) {
            statusField.setReadOnly(true);
        }
        User user = userService.getCurrentUser();
        System.out.println("user " + user);

        boolean isPatient = userService.hasRole("patient-role");
        System.out.println("isPatient " + isPatient);
        if(isPatient) {
            // Tìm patient tương ứng
            Optional<Patient> currentPatientOpt = patientRepository.findByAccount(user);
            System.out.println("Patient" + currentPatientOpt);
            if (currentPatientOpt.isPresent()) {
                Patient currentPatient = currentPatientOpt.get();

                getEditedEntity().setPatient(currentPatient);
                patientField.setValue(currentPatient);
                patientField.setReadOnly(true);
                patientField.setEnabled(false);
                patientField.getActions().forEach(a -> a.setVisible(false));
            } else {

            }
        }



    }

    private boolean isNewEntity() {
        return getEditedEntity().getId() == null;
    }



}