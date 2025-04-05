package com.company.medicalappointmentsystem.view.payment;

import com.company.medicalappointmentsystem.entity.Payment;
import com.company.medicalappointmentsystem.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "payments/:id", layout = MainView.class)
@ViewController(id = "Payment.detail")
@ViewDescriptor(path = "payment-detail-view.xml")
@EditedEntityContainer("paymentDc")
public class PaymentDetailView extends StandardDetailView<Payment> {
}