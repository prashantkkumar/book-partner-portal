package org.example.backend.mapper;

import org.example.backend.dto.TitleauthorDto;
import org.example.backend.dto.TitleauthorIdDto;
import org.example.backend.entities.Titleauthor;
import org.example.backend.entities.TitleauthorId;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TitleauthorMapper {

    // DTO + ID DTO → Entity
    @Mapping(target = "id.auId", source = "idDto.auId")
    @Mapping(target = "id.titleId", source = "idDto.titleId")
    @Mapping(target = "au.auId", source = "idDto.auId")
    @Mapping(target = "title.titleId", source = "idDto.titleId")
    Titleauthor toEntity(TitleauthorDto dto, TitleauthorIdDto idDto);

    // Entity → DTO
    @Mapping(target = "authorName", source = "au.auLname")
    @Mapping(target = "titleName", source = "title.title")
    TitleauthorDto toDto(Titleauthor entity);

    TitleauthorIdDto toIdDto(TitleauthorId id);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(TitleauthorDto dto, @MappingTarget Titleauthor entity);
}
