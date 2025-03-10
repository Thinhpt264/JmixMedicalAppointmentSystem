
package com.company.medicalappointmentsystem.view.medicine;

import com.company.medicalappointmentsystem.entity.Medicine;

import com.company.medicalappointmentsystem.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "medicines/:id", layout = MainView.class)
@ViewController(id = "Medicine.detail")
@ViewDescriptor(path = "medicine-detail-view.xml")
@EditedEntityContainer("medicineDc")
public class MedicineDetailView extends StandardDetailView<Medicine> {
}