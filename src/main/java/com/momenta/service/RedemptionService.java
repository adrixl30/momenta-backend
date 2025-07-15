package com.momenta.service;

import com.momenta.dto.RedemptionResponseDTO;
import com.momenta.model.Redemption;
import com.momenta.model.User;
import com.momenta.repository.RedemptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RedemptionService {

    @Autowired
    private RedemptionRepository redemptionRepository;

    @Autowired
    private UserService userService;

    public RedemptionResponseDTO useCode(String code, String email) {
        Redemption redemption = redemptionRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Código no válido"));

        if (redemption.isUsed()) {
            throw new IllegalStateException("Este código ya fue usado.");
        }

        if (redemption.getExpirationDate() != null &&
                redemption.getExpirationDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("El código ha expirado.");
        }

        User user = (User) userService.loadUserByUsername(email);

        redemption.setUsed(true);
        redemption.setRedemptionDate(LocalDate.now());
        redemption.setUser(user);

        redemptionRepository.save(redemption);

        return new RedemptionResponseDTO(
                redemption.getExperience().getTitle(),
                redemption.getExperience().getCity(),
                redemption.getExperience().getProvider().getName(),
                redemption.getExperience().getProvider().getContact(),
                redemption.getExperience().getProvider().getConditions(),
                redemption.getExpirationDate(),
                redemption.getExperience().isOnlineReservation()
        );
    }
}
