package dev.fernandocarvalho.payments.controller;


import dev.fernandocarvalho.payments.controller.dto.PaymentProcessDTO;
import dev.fernandocarvalho.payments.domain.Payment;
import dev.fernandocarvalho.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/payments")
public class PaymentController {


    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentProcessDTO> pay() {
        Payment payment = paymentService.pay();
        PaymentProcessDTO paymentProcessDTO = new PaymentProcessDTO(payment.getId(), payment.getStatus());
        return ResponseEntity.ok(paymentProcessDTO);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<PaymentProcessDTO> getStatus(@PathVariable("id") UUID id) {
        Payment payment = paymentService.checkStatus(id);
        PaymentProcessDTO paymentProcessDTO = new PaymentProcessDTO(payment.getId(), payment.getStatus());
        return ResponseEntity.ok(paymentProcessDTO);
    }
}
