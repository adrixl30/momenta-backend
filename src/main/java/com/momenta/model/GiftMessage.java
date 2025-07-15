package com.momenta.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gift_messages")
public class GiftMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fromName;
    private String toName;
    private String message;
    private String imageUrl;
    private String occasion;
    private boolean reminderNextYear;

    private String code; // Código único de canje

    private LocalDate createdAt;
    private boolean opened = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    private Experience experience;

    @ManyToOne
    @JoinColumn(name = "giftbox_id")
    private GiftBox giftbox;

    // Getters y setters
}
