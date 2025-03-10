
package com.company.medicalappointmentsystem.view.medicine;

import com.company.medicalappointmentsystem.entity.Medicine;

import com.company.medicalappointmentsystem.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "medicines", layout = MainView.class)
@ViewController(id = "Medicine.list")
@ViewDescriptor(path = "medicine-list-view.xml")
@LookupComponent("medicinesDataGrid")
@DialogMode(width = "64em")
public class MedicineListView extends StandardListView<Medicine> {
}