package com.inditex.prices.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.inditex.prices.domain.repository.PriceRepository;
import com.inditex.prices.infrastructure.repository.Price;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

public class DomainPriceServiceUnitTest {

    @Autowired
    private PriceRepository repository;
    private DomainPriceService service;

    @BeforeEach
    void setUp() {
        repository = mock(PriceRepository.class);
        service = new DomainPriceService(repository);
    }

    @Disabled
    @Test
    void shouldCreatePrice_thenSave() {
        final Price price = Price.builder()
                .brandId(2)
                .startDate(LocalDateTime.now().minusDays(2))
                .endDate(LocalDateTime.now().plusDays(2))
                .productId(27)
                .priority(9)
                .price(28.99)
                .currency("EUR")
                .build();

        service.createPrice(price);

        verify(repository).save(any(Price.class));
    }

    // TODO: añadir caso consulta precio

}
