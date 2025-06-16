package com.momenta.controller;

import com.momenta.dto.UserResponseDTO;
import com.momenta.model.User;
import com.momenta.security.JwtService;
import com.momenta.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService; // 🔄 Cambio de JwtUtil a JwtService

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody @Valid User user) {
        User savedUser = userService.register(user);
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getName()
        );
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        boolean success = userService.login(email, password);
        Map<String, Object> response = new HashMap<>();

        if (success) {
            UserDetails userDetails = userService.loadUserByUsername(email);
            String token = jwtService.generateToken(userDetails); // ✅ Usar JwtService

            User user = (User) userDetails;

            response.put("token", token);
            response.put("message", "Login exitoso");
            response.put("role", user.getRole().name());
            response.put("email", user.getEmail());
            response.put("name", user.getName());

        } else {
            response.put("message", "Credenciales inválidas");
        }

        return response;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> getProfile(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok("Bienvenido a tu perfil protegido 🔒");
    }
}

