package org.example.backend.mapper;

import org.example.backend.dto.SaleDto;
import org.example.backend.dto.SaleIdDto;
import org.example.backend.entities.Sale;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleMapper {

    @Mapping(target = "id.storId", source = "saleIdDto.storId")
    @Mapping(target = "id.ordNum", source = "saleIdDto.ordNum")
    @Mapping(target = "id.titleId", source = "saleIdDto.titleId")
    @Mapping(target = "stor.storId", source = "saleIdDto.storId")
    @Mapping(target = "title.titleId", source = "saleIdDto.titleId")
    Sale toEntity(SaleDto saleDto, SaleIdDto saleIdDto);

    @Mapping(source = "id.storId", target = "storId")
    @Mapping(source = "id.ordNum", target = "ordNum")
    @Mapping(source = "id.titleId", target = "titleId")
    SaleIdDto toIdDto(Sale sale);

    SaleDto toDto(Sale sale);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sale partialUpdate(SaleDto dto, @MappingTarget Sale entity);
}
