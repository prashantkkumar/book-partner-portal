package org.example.backend.mapper;

import org.example.backend.dto.AuthorDto;
import org.example.backend.entities.Author;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface AuthorMapper {
    Author toEntity(AuthorDto dto);
    AuthorDto toDto(Author entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(AuthorDto dto, @MappingTarget Author entity);
}
