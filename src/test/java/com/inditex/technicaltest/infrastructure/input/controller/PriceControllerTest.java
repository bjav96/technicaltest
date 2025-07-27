package com.inditex.technicaltest.infrastructure.input.controller;

import com.inditex.technicaltest.domain.service.impl.PriceService;
import com.inditex.technicaltest.infrastructure.input.controller.dto.response.PriceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @Test
    void test1() {
        PriceResponse mockResponse = createMockResponse(35455L, 1L, 1, "2020-06-14T00:00:00", "2020-12-31T23:59:59", "35.50", "EUR");
        when(priceService.getPrice(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0))).thenReturn(mockResponse);

        ResponseEntity<PriceResponse> response = priceController.findApplicablePrice(
                LocalDateTime.of(2020, 6, 14, 10, 0), 35455L, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void test2() {
        PriceResponse mockResponse = createMockResponse(35455L, 1L, 2, "2020-06-14T15:00:00", "2020-06-14T18:30:00", "25.45", "EUR");
        when(priceService.getPrice(35455L, 1L, LocalDateTime.of(2020, 6, 14, 16, 0))).thenReturn(mockResponse);

        ResponseEntity<PriceResponse> response = priceController.findApplicablePrice(
                LocalDateTime.of(2020, 6, 14, 16, 0), 35455L, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void test3() {
        PriceResponse mockResponse = createMockResponse(35455L, 1L, 1, "2020-06-14T00:00:00", "2020-12-31T23:59:59", "35.50", "EUR");
        when(priceService.getPrice(35455L, 1L, LocalDateTime.of(2020, 6, 14, 21, 0))).thenReturn(mockResponse);

        ResponseEntity<PriceResponse> response = priceController.findApplicablePrice(
                LocalDateTime.of(2020, 6, 14, 21, 0), 35455L, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void test4() {
        PriceResponse mockResponse = createMockResponse(35455L, 1L, 3, "2020-06-15T00:00:00", "2020-06-15T11:00:00", "30.50", "EUR");
        when(priceService.getPrice(35455L, 1L, LocalDateTime.of(2020, 6, 15, 10, 0))).thenReturn(mockResponse);

        ResponseEntity<PriceResponse> response = priceController.findApplicablePrice(
                LocalDateTime.of(2020, 6, 15, 10, 0), 35455L, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void test5() {
        PriceResponse mockResponse = createMockResponse(35455L, 1L, 4, "2020-06-15T16:00:00", "2020-12-31T23:59:59", "38.95", "EUR");
        when(priceService.getPrice(35455L, 1L, LocalDateTime.of(2020, 6, 16, 21, 0))).thenReturn(mockResponse);

        ResponseEntity<PriceResponse> response = priceController.findApplicablePrice(
                LocalDateTime.of(2020, 6, 16, 21, 0), 35455L, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    private PriceResponse createMockResponse(Long productId, Long brandId, int priceList, String startDate, String endDate, String price, String currency) {
        PriceResponse response = new PriceResponse();
        response.setProductId(productId);
        response.setBrandId(brandId);
        response.setPriceList(priceList);
        response.setStartDate(LocalDateTime.parse(startDate));
        response.setEndDate(LocalDateTime.parse(endDate));
        response.setPrice(new BigDecimal(price));
        response.setCurrency(currency);
        return response;
    }
}