package com.company.medicalappointmentsystem.view.payment;

import com.company.medicalappointmentsystem.entity.Payment;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "payments", layout = MainView.class)
@ViewController(id = "Payment.list")
@ViewDescriptor(path = "payment-list-view.xml")
@LookupComponent("paymentsDataGrid")
@DialogMode(width = "64em")
public class PaymentListView extends StandardListView<Payment> {
}