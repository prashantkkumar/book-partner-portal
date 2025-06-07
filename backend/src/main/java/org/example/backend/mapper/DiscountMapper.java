package org.example.backend.mapper;

import org.example.backend.dto.DiscountDto;
import org.example.backend.entities.Discount;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountMapper {
    Discount toEntity(DiscountDto dto);

    DiscountDto toDto(Discount entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Discount partialUpdate(DiscountDto dto, @MappingTarget Discount entity);
}
