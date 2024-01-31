package dev.fernandocarvalho.payments.service;

import dev.fernandocarvalho.contracts.PaymentProcessed;
import dev.fernandocarvalho.kafkacommons.model.Message;
import dev.fernandocarvalho.kafkacommons.model.Topics;
import dev.fernandocarvalho.payments.exception.PaymentNotFoundException;
import dev.fernandocarvalho.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentProcessedKafkaListener {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentProcessedKafkaListener(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @KafkaListener(topics = Topics.PAYMENT_PROCESSED_TOPIC, groupId = "dev_fernandocarvalho_PAYMENT_SERVICE")
    public void listen(Message<PaymentProcessed> record) {
        this.process(record);
    }

    private void process(Message<PaymentProcessed> message) {
        PaymentProcessed paymentProcessed = message.getPayload();
        UUID paymentId = UUID.fromString(paymentProcessed.getPaymentId());
        paymentRepository.findById(paymentId)
                .ifPresentOrElse(payment -> {
                    payment.confirm();
                    paymentRepository.save(payment);
                }, () -> {
                    throw new PaymentNotFoundException(paymentId);
                });

    }
}
