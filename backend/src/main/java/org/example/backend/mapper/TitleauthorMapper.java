package org.example.backend.mapper;

import org.example.backend.dto.TitleauthorDto;
import org.example.backend.entities.Titleauthor;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TitleauthorMapper {

    TitleauthorDto toDto(Titleauthor entity);

    Titleauthor toEntity(TitleauthorDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(TitleauthorDto dto, @MappingTarget Titleauthor entity);
}
