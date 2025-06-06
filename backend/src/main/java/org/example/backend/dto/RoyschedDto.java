package org.example.backend.dto;

import org.example.backend.entities.Roysched;

import java.io.Serializable;

/**
 * DTO for {@link Roysched}
 */
public record RoyschedDto(Integer id, Integer lorange, Integer hirange, Integer royalty) implements Serializable {
}