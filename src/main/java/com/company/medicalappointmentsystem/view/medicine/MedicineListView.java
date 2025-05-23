package com.company.medicalappointmentsystem.view.medicine;

import com.company.medicalappointmentsystem.entity.Medicine;
import com.company.medicalappointmentsystem.view.main.MainView;
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



@Route(value = "medicines", layout = MainView.class)
@ViewController(id = "Medicine.list")
@ViewDescriptor(path = "medicine-list-view.xml")
@LookupComponent("medicinesDataGrid")
@DialogMode(width = "64em")
public class MedicineListView extends StandardListView<Medicine> {

    @ViewComponent
    private DataGrid<Medicine> medicineDataGrid;

    @Autowired
    private UiComponents uiComponents;

    @Autowired
    private FileStorage fileStorage;


    @Supply(to = "medicinesDataGrid.photo", subject = "renderer")
    private Renderer<Medicine> medicinesDataGridImageRenderer() {
        return new ComponentRenderer<>(medicine -> {
            FileRef fileRef = medicine.getPhoto();
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