package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Roysched;

import java.io.Serializable;

/**
 * DTO for {@link Roysched}
 */
@Value
public class RoyschedDto implements Serializable {
    Integer id;
    Integer lorange;
    Integer hirange;
    Integer royalty;
}