package com.inditex.technicaltest.infrastructure.input.controller;

import com.inditex.technicaltest.domain.service.impl.PriceService;
import com.inditex.technicaltest.infrastructure.input.controller.dto.response.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    /**
     * Retrieves the applicable price for a given product and brand at a specific application date.
     *
     * @param applicationDate the date and time when the price is to be applied
     * @param productId       the ID of the product for which the price is being requested
     * @param brandId         the ID of the brand associated with the product
     * @return a ResponseEntity containing a PriceResponse with the applicable price details
     */
    @Operation(
            summary = "Retrieve applicable product price",
            description = "Returns the price of a product for a specific brand and application date."
    )
    @GetMapping("/getPrice")
    public ResponseEntity<PriceResponse> findApplicablePrice(
            @Parameter(
                    description = "Date and time when the price should be applied, format: yyyy-MM-dd HH:mm:ss",
                    example = "2020-06-14 10:00:00"
            )
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate,

            @Parameter(
                    description = "ID of the product",
                    example = "35455"
            )
            @RequestParam Long productId,

            @Parameter(
                    description = "ID of the brand",
                    example = "1"
            )
            @RequestParam Long brandId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(priceService.getPrice(productId, brandId, applicationDate));
    }
}
