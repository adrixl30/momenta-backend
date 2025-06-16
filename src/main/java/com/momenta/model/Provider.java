package com.momenta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    private String name;

    @NotBlank(message = "El contacto es obligatorio")
    private String contact;

    @NotBlank(message = "Las condiciones son obligatorias")
    private String conditions;
}
