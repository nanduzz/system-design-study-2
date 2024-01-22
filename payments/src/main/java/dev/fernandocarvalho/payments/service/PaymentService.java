package dev.fernandocarvalho.payments.service;


import dev.fernandocarvalho.payments.domain.Payment;
import dev.fernandocarvalho.payments.exception.PaymentNotFoundException;
import dev.fernandocarvalho.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment pay() {
        Payment payment = Payment.builder().build();
        return paymentRepository.save(payment);
    }

    public Payment checkStatus(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }
}
