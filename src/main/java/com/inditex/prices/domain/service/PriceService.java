package com.inditex.prices.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import com.inditex.prices.infrastructure.repository.Price;

public interface PriceService {

    Price getApplicablePrice(LocalDateTime applicationDate, int productId, int brandId);

    public List<Price> getAllPrices();

    public Price getPrice(Long id);

    public Price createPrice(Price price);

    public Price updatePrice(Long id, Price price);

    public void deletePrice(Long id);

}
