package com.momenta.service;

import com.momenta.dto.ExperienceResponseDTO;
import com.momenta.model.Experience;
import com.momenta.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository repository;

    public List<Experience> getAllExperiences() {
        return repository.findAll();
    }
    public Experience saveExperience(Experience experience) {
        return repository.save(experience);
    }

    //Recibe los parámetros desde el controlador y los pasa directo al repositorio.
    //Centraliza la lógica para mantener limpio el controlador.
    public List<Experience> getByFilters(String category, String province, BigDecimal minPrice, BigDecimal maxPrice, Integer participants) {
        System.out.println("=== FILTROS RECIBIDOS ===");
        System.out.println("Category: " + category);
        System.out.println("Province: " + province);
        System.out.println("MinPrice: " + minPrice);
        System.out.println("MaxPrice: " + maxPrice);
        System.out.println("Participants: " + participants);

        return repository.findByFilters(category, province, minPrice, maxPrice, participants);
    }

    // Este método busca la experiencia por id, actualiza sus campos y la guarda nuevamente

    public Experience updateExperience(Long id, Experience updated) {
        return repository.findById(id).map(experience -> {
            experience.setTitle(updated.getTitle());
            experience.setDescription(updated.getDescription());
            experience.setCategory(updated.getCategory());
            experience.setCity(updated.getCity());
            experience.setProvince(updated.getProvince());
            experience.setImageUrl(updated.getImageUrl());
            experience.setPrice(updated.getPrice());
            experience.setParticipants(updated.getParticipants());
            experience.setOnlineReservation(updated.isOnlineReservation());
            return repository.save(experience);
        }).orElseThrow(() -> new IllegalArgumentException("La experiencia con id " + id + " no existe."));
    }


    //🔎 Borra la experiencia del repositorio usando el id.
    public void deleteExperience(Long id) {
        repository.deleteById(id);
    }

    public List<ExperienceResponseDTO> getAllExperienceDTOs() {
        return repository.findAll().stream().map(exp ->
                new ExperienceResponseDTO(
                        exp.getTitle(),
                        exp.getDescription(),
                        exp.getCategory().getName(),
                        exp.getCity(),
                        exp.getProvince(),
                        exp.getImageUrl(), // 👈 esto va en el campo 'image' del DTO
                        exp.getPrice(),
                        exp.getParticipants(),
                        exp.isOnlineReservation() // 👈 esto va al campo 'online'
                )
        ).toList();
    }


}



// en este archivo esta toda la Lógica para guardar, actualizar, filtrar, eliminar
