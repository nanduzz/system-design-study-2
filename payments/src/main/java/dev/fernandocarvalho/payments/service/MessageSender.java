package dev.fernandocarvalho.payments.service;

public interface MessageSender<T> {

    void send(String correlationId, T message);
}
