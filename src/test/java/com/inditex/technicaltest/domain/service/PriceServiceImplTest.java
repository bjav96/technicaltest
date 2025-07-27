package com.inditex.technicaltest.domain.service;

import com.inditex.technicaltest.domain.model.Price;
import com.inditex.technicaltest.domain.repository.PriceRepository;
import com.inditex.technicaltest.domain.service.impl.PriceService;
import com.inditex.technicaltest.domain.service.PriceServiceImpl;
import com.inditex.technicaltest.infrastructure.exception.PriceNotFoundException;
import com.inditex.technicaltest.infrastructure.input.controller.dto.response.PriceResponse;
import com.inditex.technicaltest.infrastructure.output.mapper.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceServiceImpl priceService;

    private Price price;
    private PriceResponse priceResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        price = new Price();
        price.setBrandId(1L);
        price.setProductId(35455L);
        price.setPriceList(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        price.setPrice(BigDecimal.valueOf(35.50));
        price.setCurrency("EUR");

        priceResponse = new PriceResponse();
        priceResponse.setProductId(35455L);
        priceResponse.setBrandId(1L);
        priceResponse.setPriceList(1);
        priceResponse.setStartDate(price.getStartDate());
        priceResponse.setEndDate(price.getEndDate());
        priceResponse.setPrice(price.getPrice());
        priceResponse.setCurrency("EUR");
    }

    @Test
    void shouldReturnPriceWhenFound() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.findApplicablePrice(35455L, 1L, applicationDate))
                .thenReturn(Optional.of(price));
        when(priceMapper.toResponse(price))
                .thenReturn(priceResponse);

        PriceResponse response = priceService.getPrice(35455L, 1L, applicationDate);

        assertNotNull(response);
        assertEquals(35455L, response.getProductId());
        assertEquals(1, response.getPriceList());
        assertEquals(BigDecimal.valueOf(35.50), response.getPrice());
        verify(priceRepository, times(1))
                .findApplicablePrice(35455L, 1L, applicationDate);
    }

    @Test
    void shouldThrowExceptionWhenPriceNotFound() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 13, 10, 0);

        when(priceRepository.findApplicablePrice(35455L, 1L, applicationDate))
                .thenReturn(Optional.empty());

        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () ->
                priceService.getPrice(35455L, 1L, applicationDate));

        assertEquals("Price not found", exception.getMessage());
        verify(priceRepository, times(1))
                .findApplicablePrice(35455L, 1L, applicationDate);
    }
}
