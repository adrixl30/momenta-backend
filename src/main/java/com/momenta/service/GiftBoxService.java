package com.momenta.service;

import com.momenta.dto.GiftBoxRequest;
import com.momenta.model.Experience;
import com.momenta.model.GiftBox;
import com.momenta.repository.ExperienceRepository;
import com.momenta.repository.GiftBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftBoxService {

    @Autowired
    private GiftBoxRepository giftBoxRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    public List<GiftBox> getAllGiftBoxes() {
        return giftBoxRepository.findAll();
    }

    public GiftBox getGiftBoxById(Long id) {
        return giftBoxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("GiftBox con id " + id + " no encontrada"));
    }

    public GiftBox saveGiftBox(GiftBox giftBox) {
        return giftBoxRepository.save(giftBox);
    }

    public GiftBox updateGiftBox(Long id, GiftBox updatedBox) {
        return giftBoxRepository.findById(id).map(box -> {
            box.setName(updatedBox.getName());
            box.setDescription(updatedBox.getDescription());
            box.setPrice(updatedBox.getPrice());
            box.setImageUrl(updatedBox.getImageUrl());
            box.setExperiences(updatedBox.getExperiences());
            return giftBoxRepository.save(box);
        }).orElseThrow(() -> new IllegalArgumentException("GiftBox con id " + id + " no existe."));
    }

    public void deleteGiftBox(Long id) {
        giftBoxRepository.deleteById(id);
    }

    public GiftBox createFromRequest(GiftBoxRequest request) {
        List<Experience> experiences = experienceRepository.findAllById(request.getExperienceIds());

        GiftBox giftBox = GiftBox.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .experiences(experiences)
                .build();

        return giftBoxRepository.save(giftBox);
    }
}
