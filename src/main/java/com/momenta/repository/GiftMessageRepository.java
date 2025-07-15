package com.momenta.repository;

import com.momenta.model.GiftMessage;
import com.momenta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GiftMessageRepository extends JpaRepository<GiftMessage, UUID> {

    Optional<GiftMessage> findByCode(String code);

    List<GiftMessage> findBySender(User sender);
}


