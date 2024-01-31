package dev.fernandocarvalho.emailsender.kafka;


import dev.fernandocarvalho.contracts.Customer;
import dev.fernandocarvalho.contracts.PaymentProcessed;
import dev.fernandocarvalho.emailsender.application.EmailSender;
import dev.fernandocarvalho.kafkacommons.model.Message;
import dev.fernandocarvalho.kafkacommons.model.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessedKafkaListener {

    private final EmailSender emailSender;

    @Autowired
    public PaymentProcessedKafkaListener(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @KafkaListener(topics = Topics.PAYMENT_PROCESSED_TOPIC, groupId = "dev_fernandocarvalho_EMAIL_SENDER")
    public void listen(Message<PaymentProcessed> record) {
        this.process(record);
    }

    private void process(Message<PaymentProcessed> message) {
        PaymentProcessed paymentProcessed = message.getPayload();
        Customer customer = paymentProcessed.getCustomer();
        String paymentId = paymentProcessed.getPaymentId();
        emailSender.send(customer.getEmail(),
                "Your payment (%s) was processed".formatted(paymentId),
                "Payment processed");

    }


}
