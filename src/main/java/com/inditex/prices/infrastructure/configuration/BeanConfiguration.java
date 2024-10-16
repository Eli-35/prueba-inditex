package com.inditex.prices.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.inditex.prices.EliasInditexApplication;
import com.inditex.prices.domain.repository.PriceRepository;
import com.inditex.prices.domain.service.DomainPriceService;
import com.inditex.prices.domain.service.PriceService;

@Configuration
@ComponentScan(basePackageClasses = EliasInditexApplication.class)
public class BeanConfiguration {

    @Bean
    PriceService priceService(final PriceRepository priceRepository) {
        return new DomainPriceService(priceRepository);
    }
}