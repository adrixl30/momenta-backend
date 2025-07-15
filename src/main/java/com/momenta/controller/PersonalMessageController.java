package com.momenta.controller;

import com.momenta.dto.PersonalMessageRequest;
import com.momenta.service.PersonalMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personal-message")
public class PersonalMessageController {

    @Autowired
    private PersonalMessageService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonalMessageRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(service.getByRedemptionCode(code));
    }
}
