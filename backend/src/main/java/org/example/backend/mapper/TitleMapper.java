package org.example.backend.mapper;

import org.example.backend.entities.Roysched;
import org.example.backend.dto.RoyschedDto;
import org.example.backend.entities.Title;
import org.example.backend.dto.TitleDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TitleMapper {
    Title toEntity(TitleDto titleDto);

    TitleDto toDto(Title title);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Title partialUpdate(TitleDto titleDto, @MappingTarget Title title);

    Roysched toEntity(RoyschedDto royschedDto);

    RoyschedDto toDto(Roysched roysched);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Roysched partialUpdate(RoyschedDto royschedDto, @MappingTarget Roysched roysched);
}