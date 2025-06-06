package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Job;

import java.io.Serializable;

/**
 * DTO for {@link Job}
 */
@Value
public class JobDto implements Serializable {
    Short id;
    String jobDesc;
    Integer minLvl;
    Integer maxLvl;
}