package dev.fernandocarvalho.payments.controller.dto;

import dev.fernandocarvalho.payments.domain.PaymentStatus;

import java.util.UUID;

public record PaymentProcessDTO(
        UUID id,
        PaymentStatus status
) {
}
