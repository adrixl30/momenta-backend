package com.momenta.dto;

import java.math.BigDecimal;

public class ExperienceResponseDTO {
    private String title;
    private String description;
    private String category;
    private String city;
    private String province;
    private String image;
    private BigDecimal price;
    private Integer participants;
    private boolean online;

    // Constructor
    public ExperienceResponseDTO(String title, String description, String category,
                                 String city, String province, String image,
                                 BigDecimal price, Integer participants, boolean online) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.city = city;
        this.province = province;
        this.image = image;
        this.price = price;
        this.participants = participants;
        this.online = online;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getImage() { return image; }
    public BigDecimal getPrice() { return price; }
    public Integer getParticipants() { return participants; }
    public boolean isOnline() { return online; }
}
