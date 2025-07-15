package com.momenta.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromName;
    private String toName;
    private String message;

    private String mediaUrl; // imagen o video

    private LocalDateTime scheduledDelivery; // para envíos programados

    @OneToOne
    @JoinColumn(name = "redemption_id")
    private Redemption redemption;
}

