package com.momenta.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PersonalMessageRequest {
    private String fromName;
    private String toName;
    private String message;
    private String mediaUrl;
    private LocalDateTime scheduledDelivery;
    private Long redemptionId; // para enlazar al código de regalo
}
