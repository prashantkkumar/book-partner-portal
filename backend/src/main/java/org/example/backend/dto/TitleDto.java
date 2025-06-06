package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Title;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Title}
 */
@Value
public class TitleDto implements Serializable {
    String titleId;
    String title;
    String type;
    Double price;
    Double advance;
    Integer royalty;
    Integer ytdSales;
    String notes;
    Instant pubdate;
}