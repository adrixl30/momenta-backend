package com.momenta.service;

import com.momenta.model.Redemption;
import com.momenta.model.User;
import com.momenta.repository.RedemptionRepository;
import com.momenta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RedemptionService {

    @Autowired
    private RedemptionRepository redemptionRepository;

    @Autowired
    private UserService userService;

    public Redemption useCode(String code, String email) {
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

        return redemptionRepository.save(redemption);
    }
}
