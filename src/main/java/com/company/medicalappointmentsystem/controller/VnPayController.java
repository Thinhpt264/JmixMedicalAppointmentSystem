package com.company.medicalappointmentsystem.controller;


import com.company.medicalappointmentsystem.app.VnPayConfig;
import com.company.medicalappointmentsystem.entity.Payment;
import com.company.medicalappointmentsystem.entity.PaymentStatus;
import com.company.medicalappointmentsystem.entity.Prescription;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("payment")
public class VnPayController {
    @Autowired
    private VnPayConfig vnPayConfig;

    @Autowired
    private DataManager dataManager;

    @PostMapping("/pay")
    public String pay(
            @RequestParam("prescriptionId") UUID prescriptionId,
            HttpServletRequest request,
            HttpSession session
    ) {
        Prescription prescription = dataManager.load(Prescription.class).id(prescriptionId).one();

        // Lưu prescriptionId vào session để callback sau dùng
        session.setAttribute("prescriptionId", prescription.getId());

        long amount = Math.round(prescription.getTotalPrice() * 100); // VNPay yêu cầu nhân 100
        String vnp_TxnRef = vnPayConfig.getRandomNumber(8);
        String vnp_IpAddr = vnPayConfig.getIpAddress(request);

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnPayConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don thuoc: " + prescription.getPrescriptionNumber());
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", vnPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        String vnp_CreateDate = formatter.format(cld.getTime());
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        // Hash & build URL
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (Iterator<String> itr = fieldNames.iterator(); itr.hasNext(); ) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII))
                        .append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (itr.hasNext()) {
                    hashData.append('&');
                    query.append('&');
                }
            }
        }

        String vnp_SecureHash = vnPayConfig.hmacSHA512(vnPayConfig.secretKey, hashData.toString());
        query.append("&vnp_SecureHash=").append(vnp_SecureHash);
        String paymentUrl = vnPayConfig.vnp_PayUrl + "?" + query;

        return "redirect:" + paymentUrl;
    }

    @GetMapping("/vnPay-callback")
    public String callback(
            @RequestParam("vnp_Amount") String amount,
            @RequestParam("vnp_ResponseCode") String responseCode,
            @RequestParam("vnp_TransactionNo") String txnNo,
            @RequestParam("vnp_PayDate") String payDate,
            HttpSession session
    ) {
        UUID prescriptionId = (UUID) session.getAttribute("prescriptionId");
        if (prescriptionId == null) {
            return "redirect:/prescriptions"; // fallback
        }

        Prescription prescription = dataManager.load(Prescription.class).id(prescriptionId).one();

        Payment payment = dataManager.create(Payment.class);
        payment.setPrescription(prescription);
        payment.setTransactionId(txnNo);
        payment.setAmount(Double.parseDouble(amount) / 100);
        payment.setMethod("VNPAY");
        payment.setPaymentTime(LocalDateTime.now());
        payment.setStatus("00".equals(responseCode) ? "SUCCESS" : "FAILED");

        if ("00".equals(responseCode)) {
            prescription.setPaymentStatus(PaymentStatus.PAID); // Enum

        }

        // Save both Payment + Prescription
        dataManager.save(new SaveContext().saving(payment).saving(prescription));

        return "redirect:/prescriptions"; // sau thanh toán quay về danh sách đơn
    }

}
