package org.example.backend.dto;

import org.example.backend.entities.Title;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Title}
 */
public record TitleDto(String titleId, String title, String type, Double price, Double advance, Integer royalty,
                       Integer ytdSales, String notes, Instant pubdate) implements Serializable {
}