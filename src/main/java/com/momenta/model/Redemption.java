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
public class Redemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private LocalDate redemptionDate;

    private boolean used;

    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Experience experience;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @PrePersist
    protected void onRedeem() {
        this.redemptionDate = LocalDate.now();
    }
}
