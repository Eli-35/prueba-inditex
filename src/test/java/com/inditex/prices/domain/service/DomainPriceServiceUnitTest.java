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

    @BeforeEach
    void setUp() {
        repository = mock(PriceRepository.class);
        service = new DomainPriceService(repository);
    }

    // @Disabled
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

    @Test
    void givenPriceExists_whenGetApplicablePrice_thenReturnPrice() {
        // given
        final LocalDateTime date = LocalDateTime.now();
        final int brandId = 2;
        final int productId = 27;
        final double priceValue = 28.99;
        final Price price1 = Price.builder()
                .brandId(brandId)
                .startDate(date.minusDays(2))
                .endDate(date.plusDays(2))
                .productId(productId)
                .priority(9)
                .price(priceValue)
                .currency("EUR")
                .build();
        service.createPrice(price1);

        final Price price2 = Price.builder()
                .brandId(brandId)
                .startDate(date.minusDays(1))
                .endDate(date.plusDays(3))
                .productId(productId)
                .priority(10)
                .price(priceValue - 2)
                .currency("EUR")
                .build();
        service.createPrice(price2);
        verify(repository, times(2)).save(any(Price.class));

        // when
        when(repository.findApplicablePrice(date, productId, brandId)).thenReturn(Optional.of(price2));
        Price applicablePrice = service.getApplicablePrice(date, productId, brandId);

        // then
        assertNotNull(applicablePrice);
        assertEquals(price2.getPrice(), applicablePrice.getPrice());
        assertEquals(price2.getPriceId(), applicablePrice.getPriceId());
    }

}
