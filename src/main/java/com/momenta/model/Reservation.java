package com.momenta.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Experience experience;

    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status; // Ej: PENDING, CONFIRMED, CANCELLED
}
