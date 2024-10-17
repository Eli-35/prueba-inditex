package com.inditex.prices.infrastructure.repository;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
// import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "PRICES")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long priceId; // rendombrado de 'PRICE_LIST' por ser más representativo de su función
    private int brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int productId;
    private int priority;
    private double price;
    private String currency; // renombrado de 'CURR' por claridad

    public Price(Long id, Price priceElem) {
        this.priceId = id;
        this.brandId = priceElem.getBrandId();
        this.startDate = priceElem.getStartDate();
        this.endDate = priceElem.getEndDate();
        this.productId = priceElem.getProductId();
        this.priority = priceElem.getPriority();
        this.price = priceElem.getPrice();
        this.currency = priceElem.getCurrency();
    }
}
