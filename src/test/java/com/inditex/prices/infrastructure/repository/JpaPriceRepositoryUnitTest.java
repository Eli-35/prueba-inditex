package com.inditex.prices.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inditex.prices.domain.repository.PriceRepository;

@Disabled
@DataJpaTest
public class JpaPriceRepositoryUnitTest {

    // TODO: completar con otros tests

    @Autowired
    private PriceRepository priceRepository;

    @Test
    public void testFindApplicablePrice() {
        Optional<Price> price = priceRepository.findApplicablePrice(
                LocalDateTime.parse("2020-06-14T10:00:00"),
                35455,
                1);
        assertNotNull(price);
        assertEquals(35.50, price.get().getPrice());
    }
}
