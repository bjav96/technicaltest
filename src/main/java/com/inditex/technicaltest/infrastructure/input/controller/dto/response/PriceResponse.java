package com.inditex.technicaltest.infrastructure.input.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
public class PriceResponse {
    public Long productId;
    public Long brandId;
    public Integer priceList;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
    public BigDecimal price;
    public String currency;
}