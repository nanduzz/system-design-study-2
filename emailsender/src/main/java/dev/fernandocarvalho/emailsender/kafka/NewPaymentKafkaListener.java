package dev.fernandocarvalho.emailsender.kafka;


import dev.fernandocarvalho.contracts.Customer;
import dev.fernandocarvalho.contracts.NewPayment;
import dev.fernandocarvalho.emailsender.application.EmailSender;
import dev.fernandocarvalho.kafkacommons.model.Message;
import dev.fernandocarvalho.kafkacommons.model.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NewPaymentKafkaListener {

    private final EmailSender emailSender;

    @Autowired
    public NewPaymentKafkaListener(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @KafkaListener(topics = Topics.NEW_PAYMENT_TOPIC, groupId = "dev_fernandocarvalho_EMAIL_SENDER")
    public void listen(Message<NewPayment> record) {
        this.process(record);
    }



    private void process(Message<NewPayment> message) {
        NewPayment payload = message.getPayload();
        Customer customer = payload.getCustomer();

        emailSender.send(customer.getEmail(), "Your payment is being processed", "Payment being processed");

    }


}
