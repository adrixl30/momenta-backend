package com.momenta.model;

public enum ReservationStatus {
    PENDING,
    CONFIRMED,
    CANCELLED;

    public static ReservationStatus fromString(String value) {
        try {
            return ReservationStatus.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Estado de reserva inválido: " + value);
        }
    }
}
