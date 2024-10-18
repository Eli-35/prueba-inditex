package com.inditex.prices.domain.service;

import org.springframework.stereotype.Service;

import com.inditex.prices.domain.DomainException;
import com.inditex.prices.infrastructure.repository.Price;
import com.inditex.prices.domain.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DomainPriceService implements PriceService {

    private final PriceRepository priceRepository;

    public DomainPriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getApplicablePrice(LocalDateTime applicationDate, int productId, int brandId) {
        return priceRepository.findApplicablePrice(applicationDate, productId, brandId)
                .orElseThrow(() -> new DomainException(
                        "Price with PRODUCT_ID=" + productId + ", BRAND_ID=" + brandId +
                                " does not exist for DATE=" + applicationDate + "."));
    }

    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    public Price getPrice(Long id) {
        return priceRepository.findById(id)
                .orElseThrow(() -> new DomainException("Price with PRICE_ID=" + id + " does not exist."));
    }

    public Price createPrice(Price price) {
        return priceRepository.save(price);
    }

    public Price updatePrice(Long id, Price priceDetails) {
        // verificar que existe un precio con este ID
        if (!priceRepository.existsById(id)) {
            throw new DomainException("Price with PRICE_ID=" + id + " does not exist.");
        }

        // actualizar todos los valores del precio existente
        Price price = new Price(id, priceDetails);

        // devolver el precio actualizado
        return priceRepository.save(price);
    }

    public void deletePrice(Long id) {
        // validar que existe
        if (!priceRepository.existsById(id)) {
            throw new DomainException("Price with PRICE_ID=" + id + " does not exist.");
        }

        priceRepository.deleteById(id);
    }
}
