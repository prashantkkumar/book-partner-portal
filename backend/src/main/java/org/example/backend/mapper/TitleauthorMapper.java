package org.example.backend.mapper;

import org.example.backend.dto.TitleauthorDto;
import org.example.backend.dto.TitleauthorIdDto;
import org.example.backend.entities.Author;
import org.example.backend.entities.Title;
import org.example.backend.entities.Titleauthor;
import org.example.backend.entities.TitleauthorId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface TitleauthorMapper {
    // Convert a TitleauthorDto + TitleauthorIdDto to a Titleauthor entity
    @Mapping(target = "id.auId", source = "idDto.auId")
    @Mapping(target = "id.titleId", source = "idDto.titleId")
    @Mapping(target = "au.auId", source = "idDto.auId")
    @Mapping(target = "title.titleId", source = "idDto.titleId")
    Titleauthor toEntity(TitleauthorDto dto, TitleauthorIdDto idDto);

    TitleauthorDto toDto(Titleauthor entity);

    TitleauthorIdDto toIdDto(TitleauthorId id);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(TitleauthorDto dto, @MappingTarget Titleauthor entity);
}
