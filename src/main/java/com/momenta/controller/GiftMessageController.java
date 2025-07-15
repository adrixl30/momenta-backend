package com.momenta.controller;

import com.momenta.dto.GiftMessageRequest;
import com.momenta.dto.GiftMessageResponse;
import com.momenta.dto.RedemptionRequest;
import com.momenta.model.GiftMessage;
import com.momenta.service.GiftMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gifts")
public class GiftMessageController {

    @Autowired
    private GiftMessageService giftMessageService;

    // Crear regalo personalizado
    @PostMapping
    public ResponseEntity<GiftMessage> createGift(@RequestBody GiftMessageRequest request, Authentication authentication) {
        String senderEmail = authentication.getName();
        GiftMessage createdGift = giftMessageService.create(request, senderEmail);
        return ResponseEntity.ok(createdGift);
    }
    // recien modif
    @PostMapping("/api/redemption/{code}")
    public ResponseEntity<String> redeemGift(@PathVariable String code, @RequestBody RedemptionRequest request) {
        giftMessageService.redeemGift(code, request);
        return ResponseEntity.ok("Experiencia canjeada con éxito 🎉");
    }

    // Obtener regalo por código
    @GetMapping("/{code}")
    public ResponseEntity<GiftMessageResponse> getGiftByCode(@PathVariable String code) {
        GiftMessageResponse response = giftMessageService.getGiftMessageByCode(code);
        return ResponseEntity.ok(response);
    }
    // nos muestra el regalo abierto
    @PutMapping("/{code}/open")
    public ResponseEntity<Void> markGiftAsOpened(@PathVariable String code) {
        giftMessageService.markAsOpened(code);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/my")
    public ResponseEntity<List<GiftMessageResponse>> getMyGiftMessages(Authentication authentication) {
        String senderEmail = authentication.getName();
        return ResponseEntity.ok(giftMessageService.getGiftsBySender(senderEmail));
    }



}
