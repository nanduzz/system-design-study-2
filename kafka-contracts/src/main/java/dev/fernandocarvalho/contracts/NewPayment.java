package dev.fernandocarvalho.contracts;

import java.util.UUID;

public class NewPayment {

    private UUID paymentId;
    private PaymentStatus status;

    private Customer customer;

    public NewPayment() {
    }

    public NewPayment(UUID paymentId, PaymentStatus status, Customer customer) {
        this.paymentId = paymentId;
        this.status = status;
        this.customer = customer;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }


}
