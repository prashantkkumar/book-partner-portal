package org.example.backend.mapper;

import org.example.backend.dto.DiscountDto;
import org.example.backend.entities.Discount;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    Discount toEntity(DiscountDto dto);

    DiscountDto toDto(Discount entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(DiscountDto dto, @MappingTarget Discount entity);
}
