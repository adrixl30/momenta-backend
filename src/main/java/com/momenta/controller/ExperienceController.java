package com.momenta.controller;

import com.momenta.model.Experience;
import com.momenta.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/experiences")
@CrossOrigin(origins = "*")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @PreAuthorize("hasAnyRole('ADMIN', 'PROVIDER')")
    @PostMapping
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) {
        return ResponseEntity.ok(experienceService.saveExperience(experience));
    }

    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperiences() {
        return ResponseEntity.ok(experienceService.getAllExperiences());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Experience>> getByFilters(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String province,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer participants
    ) {
        return ResponseEntity.ok(experienceService.getByFilters(category, province, minPrice, maxPrice, participants));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROVIDER')")
    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long id, @RequestBody Experience experience) {
        return ResponseEntity.ok(experienceService.updateExperience(id, experience));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dto")
    public ResponseEntity<List<?>> getAllExperienceDTOs() {
        return ResponseEntity.ok(experienceService.getAllExperienceDTOs());
    }
}
