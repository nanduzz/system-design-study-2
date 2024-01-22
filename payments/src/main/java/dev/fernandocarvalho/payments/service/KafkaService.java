package dev.fernandocarvalho.payments.service;

import dev.fernandocarvalho.kafkacommons.model.CorrelationId;
import dev.fernandocarvalho.kafkacommons.model.Message;
import dev.fernandocarvalho.payments.domain.Payment;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaService implements MessageSender<Message<Payment>> {

    private final KafkaTemplate<String, Message<Payment>> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Message<Payment>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T> Message<T> buildMessage(T payload) {
        return new Message<>(new CorrelationId("Payment"), payload);
    }

    @Override
    public void send(String destination, String correlationId, Message<Payment> message) {
        kafkaTemplate.send(destination, correlationId, message);
    }
}
