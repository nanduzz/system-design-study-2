package dev.fernandocarvalho.kafkacommons.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorrelationIdTest {


    @Test
    void shouldCreateCorrelationId() {
        CorrelationId correlationId = new CorrelationId("title");
        assertNotNull(correlationId);
        assertNotNull(correlationId.getId());
        assertTrue(correlationId.getId().contains("title"));
    }

    @Test
    void shouldCreateCorrelationIdWithContinueWith() {
        CorrelationId correlationId = new CorrelationId("title").continueWith("title2");
        assertNotNull(correlationId);
        assertNotNull(correlationId.getId());
        assertTrue(correlationId.getId().contains("title"));
        assertTrue(correlationId.getId().contains("title2"));
    }

}