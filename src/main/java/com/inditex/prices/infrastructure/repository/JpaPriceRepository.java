package com.inditex.prices.infrastructure.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.prices.domain.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface JpaPriceRepository extends JpaRepository<Price, Long>, PriceRepository {

    // Implementación de la lógica personalizada utilizando JPQL para
    // encontrar el precio de un producto en una fecha y hora concretas
    @Override
    @Query("SELECT p " + "FROM Price p  " + "WHERE p.brandId = :brandId " + "  AND p.productId = :productId "
            + "  AND :applicationDate BETWEEN p.startDate AND p.endDate " + "ORDER BY p.priority DESC LIMIT 1 ")
    Optional<Price> findApplicablePrice(LocalDateTime applicationDate, int productId, int brandId);

}