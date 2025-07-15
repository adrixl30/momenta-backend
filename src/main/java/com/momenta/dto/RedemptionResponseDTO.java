package com.momenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RedemptionResponseDTO {
    private String experienceTitle;
    private String city;
    private String providerName;
    private String contact;
    private String conditions;
    private LocalDate expirationDate;
    private boolean onlineReservation;
}
