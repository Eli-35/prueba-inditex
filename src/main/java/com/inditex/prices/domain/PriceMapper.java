package com.inditex.prices.domain;

import org.mapstruct.Mapper;
import java.util.List;

import com.inditex.prices.infrastructure.repository.Price;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceDTO priceToPriceDTO(Price entity);

    Price priceDTOtoPrice(PriceDTO dto);

    List<PriceDTO> priceListToPriceListDTO(List<Price> entityList);

    List<Price> priceListDTOtoPriceList(List<PriceDTO> dtoList);

}
