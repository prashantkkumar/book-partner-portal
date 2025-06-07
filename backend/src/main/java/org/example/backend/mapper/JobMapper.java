package org.example.backend.mapper;
import org.example.backend.dto.JobDto;
import org.example.backend.entities.Job;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper {

    Job toEntity(JobDto dto);

    JobDto toDto(Job entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Job partialUpdate(JobDto dto, @MappingTarget Job entity);
}
