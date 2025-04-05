package com.company.medicalappointmentsystem.view.appointment;

import com.company.medicalappointmentsystem.app.UserService;
import com.company.medicalappointmentsystem.entity.*;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.email.EmailException;
import io.jmix.email.EmailInfo;
import io.jmix.email.EmailInfoBuilder;
import io.jmix.email.Emailer;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.awt.*;
import java.util.Collection;


@Route(value = "appointments", layout = MainView.class)
@ViewController(id = "Appointment.list")
@ViewDescriptor(path = "appointment-list-view.xml")
@LookupComponent("appointmentsDataGrid")
@DialogMode(width = "64em")
public class AppointmentListView extends StandardListView<Appointment> {

    @ViewComponent
    private CollectionContainer<Appointment> appointmentsDc;

    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @ViewComponent
    private CollectionLoader<Appointment> appointmentsDl;

    @ViewComponent
    private  DataGrid<Appointment> appointmentsDataGrid;

    @ViewComponent
    private JmixButton updateStatusBtn;

    @Autowired
    private UserService userService;

    @Autowired
    private Emailer emailer;

    @Subscribe(id = "updateStatusBtn", subject = "clickListener")
    public void onUpdateStatusBtnClick(final ClickEvent<JmixButton> event) throws EmailException {
        User user = userService.getCurrentUser();
        boolean isAdmin = userService.hasRole("system-full-access");
        Appointment appointment = (Appointment) appointmentsDc.getItemOrNull();
        if (appointment != null ) {
            if( user.getUsername().equals(appointment.getDoctor().getAccount().getUsername()) || isAdmin) {
                appointment.setStatus(Status.CONFIRMED);
                dataManager.save(appointment);
                String body = "Hẹn Gặp Bạn Tại Phòng Khám vào ngày " + appointment.getDate() + " với Bác sĩ "
                        + appointment.getDoctor().getName();
                EmailInfo  emailInfo = EmailInfoBuilder.create(appointment.getPatient().getEmail(), "Lịch Hẹn Của Bạn Đã Được Xác Nận",
                        body).build();
                emailer.sendEmail(emailInfo);
                Task task = dataManager.create(Task.class);
                task.setEmployee(appointment.getDoctor().getAccount());
                task.setName("Khám bệnh cho " + appointment.getPatient().getName());
                task.setStartDate(appointment.getDate());
                task.setEndDate(appointment.getDate().plusHours(2));
                task.setAppointment(appointment);
                task.setType(TaskType.SCHEDULED_CHECKUP);
                task.setStatus(TaskStatus.UPCOMING);
                dataManager.save(task); // Lưu Task
                appointmentsDl.load();

                notifications.create("Lịch hẹn đã được xác nhận")
                        .withType(Notifications.Type.SUCCESS)
                        .show();
                notifications.create("Lịch hẹn đã được thêm vào lịch làm việc")
                        .withType(Notifications.Type.SUCCESS)
                        .show();
            }else {
                notifications.create("Bạn không có quyền xác nhận lịch hẹn")
                        .withType(Notifications.Type.ERROR)
                        .withPosition(Notification.Position.TOP_END)
                        .withDuration(3000)
                        .show();
            }

        }
    }
    @Subscribe
    public void onInit(InitEvent event) {
        boolean isAdmin = userService.hasRole("system-full-access");
        boolean isDoctor = userService.hasRole("doctor-role");
        if(isAdmin || isDoctor) {
            appointmentsDataGrid.addSelectionListener(selectionEvent -> {
                boolean hasSelection = !selectionEvent.getAllSelectedItems().isEmpty();
                updateStatusBtn.setEnabled(hasSelection);
            });
        }


    }

}