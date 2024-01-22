package dev.fernandocarvalho.payments.service;

import dev.fernandocarvalho.kafkacommons.model.CorrelationId;
import dev.fernandocarvalho.kafkacommons.model.Message;
import dev.fernandocarvalho.payments.domain.Payment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService implements MessageSender<Payment> {

    private final String topic = "dev_fernandocarvalho_PAYMENT";

    private final KafkaTemplate<String, Message<?>> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Message<?>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T> Message<T> buildMessage(T payload) {
        return new Message<>(new CorrelationId("Payment"), payload);
    }

    @Override
    public void send(String correlationIdTitle, Payment message) {
        Message<Payment> data = new Message<>(new CorrelationId(correlationIdTitle), message);
        kafkaTemplate.send(topic, correlationIdTitle, data);
    }
}
