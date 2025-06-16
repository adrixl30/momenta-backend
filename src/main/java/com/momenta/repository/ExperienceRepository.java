package com.momenta.repository;

import com.momenta.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    @Query("SELECT e FROM Experience e WHERE " +
            "(:categoryName IS NULL OR LOWER(e.category.name) = LOWER(:categoryName)) AND " +
            "(:province IS NULL OR LOWER(e.province) = LOWER(:province)) AND " +
            "(:minPrice IS NULL OR e.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR e.price <= :maxPrice) AND " +
            "(:participants IS NULL OR e.participants = :participants)")
    List<Experience> findByFilters(
            @Param("categoryName") String categoryName,
            @Param("province") String province,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("participants") Integer participants);
}
