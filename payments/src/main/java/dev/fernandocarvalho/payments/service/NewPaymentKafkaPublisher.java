package dev.fernandocarvalho.payments.service;

import dev.fernandocarvalho.contracts.NewPayment;
import dev.fernandocarvalho.kafkacommons.model.CorrelationId;
import dev.fernandocarvalho.kafkacommons.model.Message;
import dev.fernandocarvalho.kafkacommons.model.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NewPaymentKafkaPublisher implements MessageSender<NewPayment> {

    private final KafkaTemplate<String, Message<?>> kafkaTemplate;


    @Autowired
    public NewPaymentKafkaPublisher(KafkaTemplate<String, Message<?>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    public void send(String correlationIdTitle, NewPayment message) {
        Message<NewPayment> data = buildMessage(message);
        kafkaTemplate.send(Topics.NEW_PAYMENT_TOPIC, "1", data);
    }

    private Message<NewPayment> buildMessage(NewPayment payload) {
        return new Message<>(new CorrelationId("Payment"), payload);
    }

}
