package com.inditex.technicaltest.domain.service;

import com.inditex.technicaltest.domain.repository.PriceRepository;
import com.inditex.technicaltest.domain.service.impl.PriceService;
import com.inditex.technicaltest.infrastructure.exception.PriceNotFoundException;
import com.inditex.technicaltest.infrastructure.input.controller.dto.response.PriceResponse;
import com.inditex.technicaltest.infrastructure.output.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PriceServiceImpl implements PriceService {


    private final PriceRepository priceRepository;
    private final PriceMapper mapper;

    /**
     * Retrieves the applicable price for a given product and brand at a specific application date.
     *
     * @param productId       the ID of the product for which the price is being requested
     * @param brandId         the ID of the brand associated with the product
     * @param applicationDate the date and time when the price is to be applied
     * @return a {@link List<PriceResponse>} containing the applicable price details
     * @throws PriceNotFoundException if no applicable price is found for the given parameters
     */
    @Override
    public PriceResponse getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return mapper.toResponse(
                priceRepository.findApplicablePrice(productId, brandId, applicationDate)
                        .orElseThrow(() -> new PriceNotFoundException("Price not found", HttpStatus.NOT_FOUND))
        );
    }
}
