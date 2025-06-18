package com.momenta.controller;

import com.momenta.model.Redemption;
import com.momenta.service.RedemptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redemptions")
public class RedemptionController {

    @Autowired
    private RedemptionService redemptionService;

    @PostMapping("/use-code")
    public ResponseEntity<?> useRedemptionCode(@RequestParam String code, Authentication authentication) {
        String email = authentication.getName();
        Redemption redemption = redemptionService.useCode(code, email);
        return ResponseEntity.ok(redemption);
    }
}
