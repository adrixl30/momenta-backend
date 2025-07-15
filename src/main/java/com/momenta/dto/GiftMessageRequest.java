package com.momenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftMessageRequest {
    private String fromName;
    private String toName;
    private String message;
    private String imageUrl;
    private String occasion;
    private boolean reminderNextYear;
    private Long experienceId; // Opcional
    private Long giftboxId;    // Opcional
}
