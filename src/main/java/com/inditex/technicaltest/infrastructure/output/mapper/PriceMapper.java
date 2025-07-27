package com.inditex.technicaltest.infrastructure.output.mapper;

import com.inditex.technicaltest.domain.model.Price;
import com.inditex.technicaltest.infrastructure.input.controller.dto.response.PriceResponse;
import com.inditex.technicaltest.infrastructure.output.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target ="brandId", source = "brand.id")
    Price toDomain(PriceEntity priceEntity);
    PriceResponse toResponse(Price price);


}
