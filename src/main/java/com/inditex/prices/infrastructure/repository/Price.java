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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    {
        // public Price() {

        // };

        // public long getPriceId() {
        // return this.priceId;
        // }

        // public int getBrandId() {
        // return this.brandId;
        // }

        // public LocalDateTime getStartDate() {
        // return this.startDate;
        // }

        // public LocalDateTime getEndDate() {
        // return this.endDate;
        // }

        // public int getProductId() {
        // return this.productId;
        // }

        // public int getPriority() {
        // return this.priority;
        // }

        // public double getPrice() {
        // return this.price;
        // }

        // public String getCurrency() {
        // return this.currency;
        // }
    }
}
