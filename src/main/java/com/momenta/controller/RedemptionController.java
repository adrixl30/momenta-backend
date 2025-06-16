package com.momenta.controller;

import com.momenta.model.Redemption;
import com.momenta.service.RedemptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/redemptions")
@CrossOrigin(origins = "*")
public class RedemptionController {

    @Autowired
    private RedemptionService redemptionService;

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/redeem")
    public ResponseEntity<Redemption> redeem(@RequestParam String code) {
        return ResponseEntity.ok(redemptionService.redeem(code));
    }
}
