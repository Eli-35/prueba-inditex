package com.inditex.prices.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inditex.prices.domain.repository.PriceRepository;
import com.inditex.prices.infrastructure.repository.Price;

public class DomainPriceServiceUnitTest {

    private PriceRepository repository;
    private DomainPriceService service;

    private final Price createTwoPricesWithDifferentPriority(LocalDateTime date, int brandId, int productId) {
        final int priority = 9;
        final double priceValue = 28.99;
        // crear dos precios de ejemplo
        final Price priceLessPriority = Price.builder()
                .brandId(brandId)
                .startDate(date.minusDays(2))
                .endDate(date.plusDays(2))
                .productId(productId)
                .priority(priority)
                .price(priceValue)
                .currency("EUR")
                .build();
        final Price priceMorePriority = Price.builder()
                .brandId(brandId)
                .startDate(date.minusDays(1))
                .endDate(date.plusDays(3))
                .productId(productId)
                .priority(priority + 1)
                .price(priceValue - 2)
                .currency("EUR")
                .build();

        service.createPrice(priceLessPriority);
        service.createPrice(priceMorePriority);

        // verificación redundante
        verify(repository, times(2)).save(any(Price.class));

        // devolver el de mayor prioridad
        return priceMorePriority;
    }

    @BeforeEach
    void setUp() {
        repository = mock(PriceRepository.class);
        service = new DomainPriceService(repository);
    }

    @Test
    void givenNewPrice_whenCreatePrice_thenSave() {
        // given
        final Price price = Price.builder()
                .brandId(2)
                .startDate(LocalDateTime.now().minusDays(2))
                .endDate(LocalDateTime.now().plusDays(2))
                .productId(27)
                .priority(9)
                .price(28.99)
                .currency("EUR")
                .build();

        // when
        service.createPrice(price);

        // then
        verify(repository).save(any(Price.class));
    }

    @Test
    void givenPriceExists_whenGetApplicablePrice_thenReturnPrice() {
        // given
        final LocalDateTime date = LocalDateTime.now();
        final int brandId = 2;
        final int productId = 27;
        final Price prioritaryPrice = createTwoPricesWithDifferentPriority(date, brandId, productId);

        // when
        when(repository.findApplicablePrice(date, productId, brandId)).thenReturn(Optional.of(prioritaryPrice));
        Price applicablePrice = service.getApplicablePrice(date, productId, brandId);

        // then
        assertNotNull(applicablePrice);
        // validar que el precio coincide con el de mayor prioridad
        assertEquals(prioritaryPrice.getPrice(), applicablePrice.getPrice());
        assertEquals(prioritaryPrice.getPriceId(), applicablePrice.getPriceId());
    }
}
