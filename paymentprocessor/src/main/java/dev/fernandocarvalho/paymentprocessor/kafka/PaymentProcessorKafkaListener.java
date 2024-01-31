package dev.fernandocarvalho.paymentprocessor.kafka;


import dev.fernandocarvalho.contracts.Customer;
import dev.fernandocarvalho.contracts.NewPayment;
import dev.fernandocarvalho.kafkacommons.model.Message;
import dev.fernandocarvalho.kafkacommons.model.Topics;
import dev.fernandocarvalho.paymentprocessor.application.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class PaymentProcessorKafkaListener {

    private final PaymentProcessor paymentProcessor;

    @Autowired
    public PaymentProcessorKafkaListener(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @KafkaListener(topics = Topics.NEW_PAYMENT_TOPIC, groupId = "dev_fernandocarvalho_PAYMENT_PROCESSOR")
    public void listen(Message<NewPayment> record) {
        this.process(record);
    }

    private void process(Message<NewPayment> message) {
        NewPayment payload = message.getPayload();
        Customer customer = payload.getCustomer();

        paymentProcessor.process(payload.getPaymentId().toString(), customer.getName(), customer.getEmail(), message.getCorrelationId());
    }


}
