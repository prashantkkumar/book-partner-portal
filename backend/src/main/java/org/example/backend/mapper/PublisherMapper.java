package org.example.backend.mapper;
import org.example.backend.dto.PublisherDto;
import org.example.backend.entities.Publisher;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PublisherMapper {

    Publisher toEntity(PublisherDto dto);

    PublisherDto toDto(Publisher entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Publisher partialUpdate(PublisherDto dto, @MappingTarget Publisher entity);
}
