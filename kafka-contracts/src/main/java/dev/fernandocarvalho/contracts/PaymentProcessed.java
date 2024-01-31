package dev.fernandocarvalho.contracts;

public class PaymentProcessed {

    private String paymentId;
    private PaymentStatus status;
    private Customer customer;

    public PaymentProcessed() {
    }

    public PaymentProcessed(String paymentId, PaymentStatus status, Customer customer) {
        this.paymentId = paymentId;
        this.status = status;
        this.customer = customer;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }
}
