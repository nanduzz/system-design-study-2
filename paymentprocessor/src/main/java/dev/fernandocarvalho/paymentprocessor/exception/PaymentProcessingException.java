package dev.fernandocarvalho.paymentprocessor.exception;

public class PaymentProcessingException extends RuntimeException {

    private final String paymentId;

    public PaymentProcessingException(String paymentId) {
        super("Error processing payment with id: " + paymentId);
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
