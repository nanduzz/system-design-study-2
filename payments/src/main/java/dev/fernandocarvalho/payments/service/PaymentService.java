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
    private final KafkaService kafkaService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, KafkaService kafkaService) {
        this.paymentRepository = paymentRepository;
        this.kafkaService = kafkaService;
    }

    public Payment pay() {
        Payment payment = Payment.builder().build();
        Payment savedPayment = paymentRepository.save(payment);
        kafkaService.send("Payment", savedPayment);

        return savedPayment;

    }

    public Payment checkStatus(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }
}
