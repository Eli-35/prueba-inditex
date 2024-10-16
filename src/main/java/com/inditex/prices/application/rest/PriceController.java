package com.inditex.prices.application.rest;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.inditex.prices.domain.DomainException;
import com.inditex.prices.domain.PriceDTO;
import com.inditex.prices.domain.PriceMapper;
import com.inditex.prices.infrastructure.repository.Price;
import com.inditex.prices.domain.service.PriceService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private final PriceService priceService;

    private final PriceMapper mapper = Mappers.getMapper(PriceMapper.class);

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    // Obtener el precio aplicable para una fecha, producto y cadena dados
    // http://localhost:8080/api/prices/search?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDTO> getApplicablePrice(
            @RequestParam String applicationDate,
            @RequestParam int productId,
            @RequestParam int brandId) {
        try {
            LocalDateTime applicationDateTime = LocalDateTime.parse(applicationDate);
            Price price = priceService.getApplicablePrice(applicationDateTime, productId, brandId);
            return ResponseEntity.ok(mapper.priceToPriceDTO(price));
        } catch (DomainException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // Obtener todos los precios
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceDTO>> getAllPrices() {
        return ResponseEntity.ok(mapper.priceListToPriceListDTO(priceService.getAllPrices()));
    }

    // Obtener un precio por su ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDTO> getPriceById(@PathVariable Long id) {
        Price price = priceService.getPrice(id);
        return ResponseEntity.ok(mapper.priceToPriceDTO(price));

    }

    // Crear un nuevo precio
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<PriceDTO> createPrice(@RequestBody PriceDTO price) {
        return ResponseEntity.ok(mapper.priceToPriceDTO(priceService.createPrice(mapper.priceDTOtoPrice(price))));
    }

    // Actualizar un precio existente
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDTO> updatePrice(@PathVariable Long id, @RequestBody PriceDTO priceDetails) {
        return ResponseEntity.ok(
                mapper.priceToPriceDTO(priceService.updatePrice(id, mapper.priceDTOtoPrice(priceDetails))));

    }

    // Eliminar un precio por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long id) {
        try {
            priceService.deletePrice(id);
        } catch (DomainException ex) {
            // asumimos que el error se debe a que no existe este precio
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
