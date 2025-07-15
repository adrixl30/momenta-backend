package com.momenta.repository;

import com.momenta.model.PersonalMessage;
import com.momenta.model.Redemption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalMessageRepository extends JpaRepository<PersonalMessage, Long> {
    Optional<PersonalMessage> findByRedemption(Redemption redemption);
}
