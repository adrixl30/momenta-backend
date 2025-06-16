package com.momenta.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class GiftBoxRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private List<Long> experienceIds;
}
