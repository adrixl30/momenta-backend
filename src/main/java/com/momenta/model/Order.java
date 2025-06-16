package com.momenta.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "orders") // <--- evita conflicto con palabra reservada
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User client;

    @ManyToOne(optional = false)
    private GiftBox giftBox;

    private String recipientName;
    private String recipientEmail;

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, PAID, CANCELED
}