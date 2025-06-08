package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Titleauthor;

import java.io.Serializable;

/**
 * DTO for {@link Titleauthor}
 */
@Value
public class TitleauthorDto implements Serializable {
    TitleauthorIdDto id;
    AuthorDto au;
    TitleDto title;
    Byte auOrd;
    Integer royaltyper;
}