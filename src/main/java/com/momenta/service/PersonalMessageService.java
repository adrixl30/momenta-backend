package com.momenta.service;

import com.momenta.dto.PersonalMessageRequest;
import com.momenta.model.PersonalMessage;
import com.momenta.model.Redemption;
import com.momenta.repository.PersonalMessageRepository;
import com.momenta.repository.RedemptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalMessageService {

    @Autowired
    private RedemptionRepository redemptionRepository;

    @Autowired
    private PersonalMessageRepository personalMessageRepository;

    public PersonalMessage create(PersonalMessageRequest request) {
        Redemption redemption = redemptionRepository.findById(request.getRedemptionId())
                .orElseThrow(() -> new RuntimeException("Redención no encontrada"));

        PersonalMessage message = PersonalMessage.builder()
                .fromName(request.getFromName())
                .toName(request.getToName())
                .message(request.getMessage())
                .mediaUrl(request.getMediaUrl())
                .scheduledDelivery(request.getScheduledDelivery())
                .redemption(redemption)
                .build();

        return personalMessageRepository.save(message);
    }

    public Optional<PersonalMessage> getByRedemptionCode(String code) {
        Redemption redemption = redemptionRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Código inválido"));

        return personalMessageRepository.findByRedemption(redemption);
    }
}
