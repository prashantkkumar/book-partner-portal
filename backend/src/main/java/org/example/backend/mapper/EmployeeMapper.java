package org.example.backend.mapper;

import org.example.backend.dto.EmployeeDto;
import org.example.backend.entities.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    // Converts Employee entity to EmployeeDto
    EmployeeDto toDto(Employee employee);

    // Converts EmployeeDto back to Employee entity
    Employee toEntity(EmployeeDto dto);
}
