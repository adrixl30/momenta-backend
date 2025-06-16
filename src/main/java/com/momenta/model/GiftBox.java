package com.momenta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    @NotBlank(message = "La imagen es obligatoria")
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "giftbox_experiences",
            joinColumns = @JoinColumn(name = "giftbox_id"),
            inverseJoinColumns = @JoinColumn(name = "experience_id")
    )
    private List<Experience> experiences;
}
