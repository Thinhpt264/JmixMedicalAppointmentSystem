package com.company.medicalappointmentsystem.view.doctor;

import com.company.medicalappointmentsystem.entity.Doctor;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "doctors", layout = MainView.class)
@ViewController(id = "Doctor.list")
@ViewDescriptor(path = "doctor-list-view.xml")
@LookupComponent("doctorsDataGrid")
@DialogMode(width = "64em")
public class DoctorListView extends StandardListView<Doctor> {
    @ViewComponent
    private DataGrid<Doctor> doctorsDataGrid;

    @Autowired
    private UiComponents uiComponents;



    @Autowired
    private FileStorage  fileStorage;

    @Supply(to = "doctorsDataGrid.image", subject = "renderer")
    private Renderer<Doctor> doctorsDataGridPictureRenderer() {
        return new ComponentRenderer<>(user -> {
            FileRef fileRef = user.getImage();
            if (fileRef != null) {
                Image image = uiComponents.create(Image.class);
                image.setWidth("50px");
                image.setHeight("50px");
                StreamResource streamResource = new StreamResource(
                        fileRef.getFileName(),
                        () -> fileStorage.openStream(fileRef));
                image.setSrc(streamResource);
                image.setClassName("doctor-picture");

                return image;
            } else {
                return null;
            }
        });
    }





}