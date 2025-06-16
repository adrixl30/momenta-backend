package com.momenta.service;

import com.momenta.model.Experience;
import com.momenta.model.Reservation;
import com.momenta.model.ReservationStatus;
import com.momenta.model.User;
import com.momenta.repository.ExperienceRepository;
import com.momenta.repository.ReservationRepository;
import com.momenta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private UserService userService;

    public Reservation createReservation(Long experienceId) {
        // Obtener usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = (User) userService.loadUserByUsername(email);

        // Obtener experiencia
        Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new IllegalArgumentException("Experiencia no encontrada"));

        // Crear reserva
        Reservation reservation = Reservation.builder()
                .user(user)
                .experience(experience)
                .reservationDate(LocalDate.now())
                .status(ReservationStatus.PENDING)
                .build();

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    public List<Reservation> getMyReservations() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = (User) userService.loadUserByUsername(email);

        return reservationRepository.findByUser(user);
    }

    public Reservation updateReservationStatus(Long reservationId, ReservationStatus status) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con ID: " + reservationId));

        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }


    // Otros métodos como: cancelarReserva, confirmarReserva, getByUser, etc., pueden añadirse después
}
