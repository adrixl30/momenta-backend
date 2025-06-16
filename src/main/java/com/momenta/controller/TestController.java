package com.momenta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT', 'PROVIDER')") // puedes ajustar según tu lógica
    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "✅ Acceso autorizado con JWT";
    }
}
