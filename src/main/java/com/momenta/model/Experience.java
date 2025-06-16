package com.momenta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    private String title;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotBlank(message = "La ciudad es obligatoria")
    private String city;

    @NotBlank(message = "La provincia es obligatoria")
    private String province;

    @NotBlank(message = "La URL de imagen es obligatoria")
    private String imageUrl;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    @NotNull(message = "Debe especificar la cantidad de participantes")
    @Min(value = 1, message = "Debe haber al menos 1 participante")
    private Integer participants;

    private boolean onlineReservation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
}
