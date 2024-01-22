package dev.fernandocarvalho.payments.service;

public interface MessageSender<T> {

    void send(String destination, String correlationId, T message);
}
