package dev.fernandocarvalho.payments.service;


import dev.fernandocarvalho.contracts.Customer;
import dev.fernandocarvalho.contracts.NewPayment;
import dev.fernandocarvalho.contracts.PaymentStatus;
import dev.fernandocarvalho.payments.domain.Payment;
import dev.fernandocarvalho.payments.exception.PaymentNotFoundException;
import dev.fernandocarvalho.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final NewPaymentKafkaPublisher newPaymentKafkaPublisher;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, NewPaymentKafkaPublisher newPaymentKafkaPublisher) {
        this.paymentRepository = paymentRepository;
        this.newPaymentKafkaPublisher = newPaymentKafkaPublisher;
    }

    public Payment pay() {
        Payment payment = Payment.builder().build();
        Payment savedPayment = paymentRepository.save(payment);
        Customer customer = new Customer("customer@getnada.com", "Customer Name");

        NewPayment newPayment = new NewPayment(savedPayment.getId(), PaymentStatus.PROCESSING, customer);
        newPaymentKafkaPublisher.send("Payment", newPayment);

        return savedPayment;

    }

    public Payment checkStatus(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }
}
