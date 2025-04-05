package com.company.medicalappointmentsystem.view.prescriptionitem;

import com.company.medicalappointmentsystem.entity.Medicine;
import com.company.medicalappointmentsystem.entity.PrescriptionItem;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "prescription-items/:id", layout = MainView.class)
@ViewController(id = "PrescriptionItem.detail")
@ViewDescriptor(path = "prescription-item-detail-view.xml")
@EditedEntityContainer("prescriptionItemDc")
public class PrescriptionItemDetailView extends StandardDetailView<PrescriptionItem> {
    @ViewComponent
    private EntityComboBox<Medicine> medicineField;

    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<PrescriptionItem> event) {
        event.getEntity().setQuantity(1);
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        // Load the list of Medicine entities
        List<Medicine> medicines = dataManager.load(Medicine.class).all().list();

        // Set the items for the medicineField
        medicineField.setItems(medicines);

        // Add a value change listener to handle selection
        medicineField.addValueChangeListener(valueChangeEvent -> {
            PrescriptionItem item = getEditedEntity();
            if (valueChangeEvent.getValue() != null) {
                Medicine selectedMedicine = valueChangeEvent.getValue();
                item.setMedicine(selectedMedicine);
            }
        });
    }
}