package com.inditex.technicaltest.infrastructure.output.adapter;

import com.inditex.technicaltest.domain.model.Price;
import com.inditex.technicaltest.domain.repository.PriceRepository;
import com.inditex.technicaltest.infrastructure.output.mapper.PriceMapper;
import com.inditex.technicaltest.infrastructure.output.repository.PriceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository jpaRepository;
    private final PriceMapper mapper;

    /**
     * Finds the first applicable price for a given product, brand, and application date.
     *
     * @param productId the ID of the product for which the price is being searched
     * @param brandId the ID of the brand associated with the product
     * @param applicationDate the date and time when the price is to be applied
     * @return an {@link Optional} containing the applicable {@link Price} if found, or an empty {@link Optional} if no price is applicable
     */
    @Override
    public Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime  applicationDate) {
        return jpaRepository.findApplicablePrices(productId, brandId, applicationDate)
                .stream()
                .findFirst()
                .map(mapper::toDomain);
    }
}
