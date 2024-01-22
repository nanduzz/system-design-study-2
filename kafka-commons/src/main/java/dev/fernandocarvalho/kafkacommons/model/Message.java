package dev.fernandocarvalho.kafkacommons.model;

public class Message<T> {

    private final CorrelationId correlationId;
    private final T payload;

    public Message(CorrelationId correlationId, T payload) {
        this.correlationId = correlationId;
        this.payload = payload;
    }

    public CorrelationId getCorrelationId() {
        return correlationId;
    }

    public T getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Message{correlationId=%s, payload=%s}".formatted(correlationId, payload);
    }
}
