package dev.fernandocarvalho.kafkacommons.model;

import java.util.UUID;

public class CorrelationId {

    private final String id;

    public CorrelationId(String title) {
        this.id = "%s(%s)".formatted(title, UUID.randomUUID());
    }

    public CorrelationId continueWith(String title) {
        return new CorrelationId("%s-%s".formatted(id, title));
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CorrelationId(id=%s)".formatted(id);
    }
}
