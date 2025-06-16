package com.momenta.repository;

import com.momenta.model.Redemption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedemptionRepository extends JpaRepository<Redemption, Long> {
    Optional<Redemption> findByCode(String code);
}
