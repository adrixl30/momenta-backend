package com.momenta.controller;

import com.momenta.model.Reservation;
import com.momenta.model.ReservationStatus;
import com.momenta.model.User;
import com.momenta.repository.ReservationRepository;
import com.momenta.service.ReservationService;
import com.momenta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationRepository reservationRepository;

    // 🔒 Crear una nueva reserva (CLIENT)
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/book")
    public ResponseEntity<Reservation> book(@RequestParam Long experienceId) {
        return ResponseEntity.ok(reservationService.createReservation(experienceId));
    }

    // 🔒 Ver todas las reservas del usuario autenticado
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/my-reservations")
    public ResponseEntity<List<Reservation>> getMyReservations() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // El email es el username
        User user = (User) userService.loadUserByUsername(email);
        List<Reservation> reservations = reservationRepository.findByUser(user);
        return ResponseEntity.ok(reservations);
    }

    // 🔐 Admin o proveedor podrían tener otro endpoint para ver todas
    @PreAuthorize("hasAnyRole('ADMIN', 'PROVIDER')")
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROVIDER')")
    @PutMapping("/{id}/status")
    public ResponseEntity<Reservation> updateStatus(
            @PathVariable Long id,
            @RequestParam ReservationStatus status
    ) {
        return ResponseEntity.ok(reservationService.updateReservationStatus(id, status));
    }

}
