package com.momenta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GiftMessageResponse {
    private String fromName;
    private String toName;
    private String message;
    private String imageUrl;
    private String occasion;
    private String code;
    private boolean opened;
}
