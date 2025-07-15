package com.momenta.service;

import com.momenta.dto.GiftMessageRequest;
import com.momenta.dto.GiftMessageResponse;
import com.momenta.dto.RedemptionRequest;
import com.momenta.model.*;
import com.momenta.repository.GiftMessageRepository;
import com.momenta.repository.ExperienceRepository;
import com.momenta.repository.GiftBoxRepository;
import com.momenta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class GiftMessageService {

    @Autowired
    private GiftMessageRepository giftMessageRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private GiftBoxRepository giftBoxRepository;

    @Autowired
    private UserRepository userRepository;

    public GiftMessage create(GiftMessageRequest request, String senderEmail) {
        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        GiftMessage gift = new GiftMessage();
        gift.setFromName(request.getFromName());
        gift.setToName(request.getToName());
        gift.setMessage(request.getMessage());
        gift.setImageUrl(request.getImageUrl());
        gift.setOccasion(request.getOccasion());
        gift.setReminderNextYear(request.isReminderNextYear());
        gift.setSender(sender);
        gift.setCreatedAt(LocalDate.now());
        gift.setCode(generateCode());
        gift.setOpened(false);

        // LOG DE DEPURACIÓN
        System.out.println("🟢 Creando nuevo GiftMessage:");
        System.out.println("ID: " + gift.getId());
        System.out.println("FROM: " + gift.getFromName() + " ➜ TO: " + gift.getToName());
        System.out.println("CODE: " + gift.getCode());

        if (request.getExperienceId() != null) {
            Experience exp = experienceRepository.findById(request.getExperienceId())
                    .orElseThrow(() -> new RuntimeException("Experiencia no encontrada"));
            gift.setExperience(exp);
        }

        if (request.getGiftboxId() != null) {
            GiftBox box = giftBoxRepository.findById(request.getGiftboxId())
                    .orElseThrow(() -> new RuntimeException("Giftbox no encontrada"));
            gift.setGiftbox(box);
        }

        return giftMessageRepository.save(gift);
    }

    public GiftMessageResponse getByCode(String code) {
        GiftMessage gift = giftMessageRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Código no válido"));

        return GiftMessageResponse.builder()
                .fromName(gift.getFromName())
                .toName(gift.getToName())
                .message(gift.getMessage())
                .imageUrl(gift.getImageUrl())
                .occasion(gift.getOccasion())
                .code(gift.getCode())
                .opened(gift.isOpened())
                .build();
    }

    public void markAsOpened(String code) {
        GiftMessage gift = giftMessageRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Código no válido"));
        gift.setOpened(true);
        giftMessageRepository.save(gift);
    }
    public List<GiftMessageResponse> getGiftsBySender(String senderEmail) {
        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<GiftMessage> gifts = giftMessageRepository.findBySender(sender);

        return gifts.stream().map(gift -> GiftMessageResponse.builder()
                .fromName(gift.getFromName())
                .toName(gift.getToName())
                .message(gift.getMessage())
                .imageUrl(gift.getImageUrl())
                .occasion(gift.getOccasion())
                .code(gift.getCode())
                .opened(gift.isOpened())
                .build()
        ).toList();
    }
    public GiftMessageResponse getGiftMessageByCode(String code) {
        GiftMessage message = giftMessageRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));

        if (!message.isOpened()) {
            message.setOpened(true);
            giftMessageRepository.save(message); // guardar el cambio
        }

        return GiftMessageResponse.builder()
                .fromName(message.getFromName())
                .toName(message.getToName())
                .message(message.getMessage())
                .imageUrl(message.getImageUrl())
                .occasion(message.getOccasion())
                .code(message.getCode())
                .opened(message.isOpened())
                .build();
    }
   // recien modif
    public void redeemGift(String code, RedemptionRequest request) {
        GiftMessage gift = giftMessageRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Código no válido"));

        if (gift.getExperience() != null) {
            throw new RuntimeException("Este regalo ya fue canjeado");
        }

        Experience exp = experienceRepository.findById(request.getExperienceId())
                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada"));

        gift.setExperience(exp);
        giftMessageRepository.save(gift);
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
