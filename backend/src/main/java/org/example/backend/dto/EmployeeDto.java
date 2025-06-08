package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Employee;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Employee}
 */
@Value
public class EmployeeDto implements Serializable {
    String empId;
    String fname;
    Character minit;
    String lname;
    Integer jobLvl;
    Instant hireDate;
     Short jobId;
     String pubId;
}