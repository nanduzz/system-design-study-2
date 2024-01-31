package dev.fernandocarvalho.paymentprocessor.application;


import dev.fernandocarvalho.contracts.Customer;
import dev.fernandocarvalho.contracts.PaymentProcessed;
import dev.fernandocarvalho.contracts.PaymentStatus;
import dev.fernandocarvalho.kafkacommons.model.CorrelationId;
import dev.fernandocarvalho.kafkacommons.model.Message;
import dev.fernandocarvalho.kafkacommons.model.Topics;
import dev.fernandocarvalho.paymentprocessor.exception.PaymentProcessingException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

    private final KafkaTemplate<String, Message<?>> kafkaTemplate;

    public static final double ERROR_CHANCE = 0.05;

    public PaymentProcessor(KafkaTemplate<String, Message<?>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void process(String paymentId, String customerName, String email, CorrelationId correlationId) {
        double errorRnd = Math.random();
        if (errorRnd < ERROR_CHANCE) {
            throw new PaymentProcessingException(paymentId);
        }

        System.out.printf("Processing payment %s for customer %s with email %s%n", paymentId, customerName, email);
        sendEvent(paymentId, customerName, email, correlationId);
    }

    private void sendEvent(String paymentId, String customerName, String email, CorrelationId correlationId) {
        Customer customer = new Customer(email, customerName);
        Message<PaymentProcessed> message = buildMessage(paymentId, correlationId, customer);
        kafkaTemplate.send(Topics.PAYMENT_PROCESSED_TOPIC, "1", message);
    }

    private static Message<PaymentProcessed> buildMessage(String payment, CorrelationId correlationId, Customer customer) {
        CorrelationId correlationIdComposed = correlationId.continueWith("PaymentProcessed");
        PaymentProcessed paymentProcessed = new PaymentProcessed(payment, PaymentStatus.CONFIRMED, customer);

        return new Message<>(correlationIdComposed, paymentProcessed);
    }
}
