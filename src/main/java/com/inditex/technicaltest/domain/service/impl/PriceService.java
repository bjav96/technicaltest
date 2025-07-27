package com.inditex.technicaltest.domain.service.impl;

import com.inditex.technicaltest.infrastructure.exception.PriceNotFoundException;
import com.inditex.technicaltest.infrastructure.input.controller.dto.response.PriceResponse;

import java.time.LocalDateTime;


public interface PriceService {

     /**
      * Retrieves the applicable price for a given product and brand at a specific application date.
      *
      * @param productId       the ID of the product for which the price is being requested
      * @param brandId         the ID of the brand associated with the product
      * @param applicationDate the date and time when the price is to be applied
      * @return a {@link PriceResponse} containing the applicable price details
      * @throws PriceNotFoundException if no applicable price is found for the given parameters
      */
     PriceResponse getPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
