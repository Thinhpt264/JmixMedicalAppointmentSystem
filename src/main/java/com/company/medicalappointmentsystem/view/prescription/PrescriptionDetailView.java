package com.company.medicalappointmentsystem.view.prescription;

import com.company.medicalappointmentsystem.entity.Medicine;
import com.company.medicalappointmentsystem.entity.Patient;
import com.company.medicalappointmentsystem.entity.Prescription;
import com.company.medicalappointmentsystem.entity.PrescriptionItem;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Route(value = "prescriptions/:id", layout = MainView.class)
@ViewController(id = "Prescription.detail")
@ViewDescriptor(path = "prescription-detail-view.xml")
@EditedEntityContainer("prescriptionDc")
public class PrescriptionDetailView extends StandardDetailView<Prescription> {

    @ViewComponent
    private CollectionContainer<PrescriptionItem> prescriptionItemsDc;

    @ViewComponent
    private InstanceContainer<Prescription> prescriptionDc;

    @Autowired
    private Metadata metadata;



    @Autowired
    private DataManager dataManager;




//    @Subscribe("createPrescriptionItemBtn")
//    public void onAddMedicine(Button event) {
//        PrescriptionItem item = metadata.create(PrescriptionItem.class);
//        item.setPrescription(prescriptionDc.getItem());
//        prescriptionItemsDc.getMutableItems().add(item);
//        updateTotalPrice();
//    }

    @Subscribe(id = "prescriptionItemsDc", target = Target.DATA_CONTAINER)
    public void onPrescriptionItemsDcCollectionChange(CollectionContainer.CollectionChangeEvent<PrescriptionItem> event) {
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        if (prescriptionItemsDc.getItems() == null || prescriptionItemsDc.getItems().isEmpty()) {
            prescriptionDc.getItem().setTotalPrice(0.0);
            return;
        }

        double total = prescriptionItemsDc.getItems().stream()
                .filter(item -> item.getMedicine() != null)
                .mapToDouble(item -> item.getMedicine().getPrice() * item.getQuantity())
                .sum();

        prescriptionDc.getItem().setTotalPrice(total);
    }

//    @Subscribe(id = "createPrescriptionItemBtn", subject = "clickListener")
//    public void onAddMedicine(final ClickEvent<JmixButton> event) {
//        PrescriptionItem item = metadata.create(PrescriptionItem.class);
//
//        Prescription prescription = prescriptionDc.getItem();
//        if (prescription == null) {
//            return;
//        }
//
//        item.setPrescription(prescription);
//    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        // Load the list of Patients entities
        List<Patient> patients = dataManager.load(Patient.class).all().list();
        Prescription prescription = getEditedEntity();
            prescription.setPrescriptionDate(LocalDate.now());



    }

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        Prescription prescription = getEditedEntity();
        if (prescription.getId() == null) {
            prescription.setId(UUID.randomUUID());
        }
    }

}