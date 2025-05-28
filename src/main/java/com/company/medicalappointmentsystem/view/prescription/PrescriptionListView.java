package com.company.medicalappointmentsystem.view.prescription;

import com.company.medicalappointmentsystem.app.PrescriptionService;
import com.company.medicalappointmentsystem.app.UserService;
import com.company.medicalappointmentsystem.entity.Prescription;
import com.company.medicalappointmentsystem.entity.User;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;

import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "prescriptions", layout = MainView.class)
@ViewController(id = "Prescription.list")
@ViewDescriptor(path = "prescription-list-view.xml")
@LookupComponent("prescriptionsDataGrid")
@DialogMode(width = "64em")
public class PrescriptionListView extends StandardListView<Prescription> {
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private CollectionContainer<Prescription> prescriptionsDc;

    @ViewComponent
    private DataGrid<Prescription> prescriptionsDataGrid;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private UserService userService;
    @Autowired
    private Notifications notifications;
    @ViewComponent
    private JmixButton payButton;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        boolean isAdmin = userService.hasRole("system-full-access");
        boolean isDoctor = userService.hasRole("doctor-role");
        if(isDoctor) {
            prescriptionsDc.setItems(prescriptionService.getPrescriptionsForDoctor());
        }else if(!isAdmin ) {
            prescriptionsDc.setItems(prescriptionService.getPrescriptionsForUser());
        }

    }

    @Subscribe(id = "payButton", subject = "clickListener")
    public void onPayButtonClick(final ClickEvent<JmixButton> event) {
        Prescription selected = prescriptionsDc.getItemOrNull();
        if(selected != null) {
            String postForm = buildAutoPostForm("/payment/pay", selected.getId().toString());
            UI.getCurrent().getPage().executeJs("document.body.insertAdjacentHTML('beforeend', $0); document.getElementById('vnpayForm').submit();", postForm);
        }

    }


    private String buildAutoPostForm(String actionUrl, String prescriptionId) {
        return "<form id='vnpayForm' method='post' action='" + actionUrl + "' style='display:none;'>" +
                "<input type='hidden' name='prescriptionId' value='" + prescriptionId + "'/>" +
                "</form>";
    }

    @Subscribe
    public void onInit(InitEvent event) {
        prescriptionsDataGrid.addSelectionListener(selectionEvent -> {
                boolean hasSelection = !selectionEvent.getAllSelectedItems().isEmpty();
                payButton.setVisible(hasSelection);
            });


    }
}