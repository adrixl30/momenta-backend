package com.momenta.controller;

import com.momenta.dto.RedemptionResponseDTO;
import com.momenta.model.Redemption;
import com.momenta.service.RedemptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redemption") // singular
public class RedemptionController {

    @Autowired
    private RedemptionService redemptionService;

    @PostMapping("/{code}")
    public ResponseEntity<?> useRedemptionCode(@PathVariable String code, Authentication authentication) {
        String email = authentication.getName();
        RedemptionResponseDTO redemption = redemptionService.useCode(code, email);
        return ResponseEntity.ok(redemption);
    }

}
