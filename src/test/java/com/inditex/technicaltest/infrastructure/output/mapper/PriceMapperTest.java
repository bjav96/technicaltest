package com.inditex.technicaltest.infrastructure.output.mapper;

import com.inditex.technicaltest.domain.model.Price;
import com.inditex.technicaltest.infrastructure.input.controller.dto.response.PriceResponse;
import com.inditex.technicaltest.infrastructure.output.entity.BrandEntity;
import com.inditex.technicaltest.infrastructure.output.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceMapperTest {

    @Autowired
    private PriceMapper priceMapper;

    @Test
    void shouldMapPriceEntityToDomain() {
        BrandEntity brand = new BrandEntity();
        brand.setId(1L);

        PriceEntity entity = new PriceEntity();
        entity.setId(100L);
        entity.setBrand(brand);
        entity.setProductId(35455L);
        entity.setPriceList(1);
        entity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        entity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        entity.setPrice(BigDecimal.valueOf(35.50));
        entity.setCurrency("EUR");

        Price price = priceMapper.toDomain(entity);

        assertNotNull(price);
        assertEquals(1L, price.getBrandId());
        assertEquals(35455L, price.getProductId());
        assertEquals(1, price.getPriceList());
        assertEquals(BigDecimal.valueOf(35.50), price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }

    @Test
    void shouldMapDomainToResponse() {
        Price price = new Price();
        price.setBrandId(1L);
        price.setProductId(35455L);
        price.setPriceList(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        price.setPrice(BigDecimal.valueOf(35.50));
        price.setCurrency("EUR");

        PriceResponse response = priceMapper.toResponse(price);

        assertNotNull(response);
        assertEquals(1L, response.getBrandId());
        assertEquals(35455L, response.getProductId());
        assertEquals(1, response.getPriceList());
        assertEquals("EUR", response.getCurrency());
        assertEquals(BigDecimal.valueOf(35.50), response.getPrice());
    }
}
