package dev.fernandocarvalho.paymentprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.fernandocarvalho.kafkacommons", "dev.fernandocarvalho.paymentprocessor"})
public class PaymentProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentProcessorApplication.class, args);
    }

}
