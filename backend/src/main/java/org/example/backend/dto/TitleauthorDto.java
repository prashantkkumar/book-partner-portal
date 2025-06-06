package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Titleauthor;

import java.io.Serializable;

/**
 * DTO for {@link Titleauthor}
 */
@Value
public class TitleauthorDto implements Serializable {
    Byte auOrd;
    Integer royaltyper;
}