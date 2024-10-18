package com.inditex.prices.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import com.inditex.prices.domain.repository.PriceRepository;

// TODO: arreglar carga datasource

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories
@EntityScan
// @ExtendWith(SpringExtension.class)
// @SpringBootTest(classes = PriceRepository.class)
// @ImportAutoConfiguration(FlywayAutoConfiguration.class)
// @AutoConfigureTestEntityManager
// @EnableAutoConfiguration(exclude = FlywayAutoConfiguration.class)
@Disabled
public class JpaPriceRepositoryUnitTest {

    // TODO: completar con otros tests

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PriceRepository priceRepository;

    @Test
    public void givenPriceExists_whenFindPriceById_thenReturnPrice() {

        final Long priceId = 33L;
        final Double priceValue = 27.0;

        // given
        Price price = Price.builder().priceId(priceId).price(priceValue).build();
        entityManager.persistAndFlush(price);
        // price = priceRepository.save(price);

        // when
        Price persistedPrice = priceRepository.findById(priceId).orElseThrow();

        // then
        assertEquals(priceValue, persistedPrice.getPrice());
    }

    @Test
    public void testFindApplicablePrice() {
        Price price = priceRepository.findApplicablePrice(
                LocalDateTime.parse("2020-06-14T10:00:00"),
                35455,
                1).orElseThrow();
        assertEquals(35.50, price.getPrice());
    }
}
