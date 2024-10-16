package com.inditex.prices.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;

import com.inditex.prices.infrastructure.repository.Price;

public interface PriceRepository {

    // Método para buscar el precio a aplicar para una fecha, producto y cadena
    Optional<Price> findApplicablePrice(LocalDateTime applicationDate, int productId, int brandId);

    // Métodos CRUD
    @NonNull
    List<Price> findAll();

    Optional<Price> findById(Long id);

    boolean existsById(Long id);

    Price save(Price price);

    void deleteById(Long id);
}