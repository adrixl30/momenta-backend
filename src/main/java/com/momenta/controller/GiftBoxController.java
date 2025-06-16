package com.momenta.controller;

import com.momenta.dto.GiftBoxRequest;
import com.momenta.model.GiftBox;
import com.momenta.service.GiftBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/giftboxes")
@CrossOrigin(origins = "*")
public class GiftBoxController {

    @Autowired
    private GiftBoxService giftBoxService;

    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT', 'PROVIDER')")
    @GetMapping
    public ResponseEntity<List<GiftBox>> getAll() {
        return ResponseEntity.ok(giftBoxService.getAllGiftBoxes());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT', 'PROVIDER')")
    @GetMapping("/{id}")
    public ResponseEntity<GiftBox> getById(@PathVariable Long id) {
        return ResponseEntity.ok(giftBoxService.getGiftBoxById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GiftBox> create(@RequestBody GiftBox giftBox) {
        return ResponseEntity.ok(giftBoxService.saveGiftBox(giftBox));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<GiftBox> update(@PathVariable Long id, @RequestBody GiftBox updatedBox) {
        return ResponseEntity.ok(giftBoxService.updateGiftBox(id, updatedBox));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        giftBoxService.deleteGiftBox(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/from-request")
    public ResponseEntity<GiftBox> createFromRequest(@RequestBody GiftBoxRequest request) {
        return ResponseEntity.ok(giftBoxService.createFromRequest(request));
    }

}
