package com.momenta.repository;

import com.momenta.model.GiftBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftBoxRepository extends JpaRepository<GiftBox, Long> {
}
