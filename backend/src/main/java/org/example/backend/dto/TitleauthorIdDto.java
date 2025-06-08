package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.TitleauthorId;

import java.io.Serializable;

/**
 * DTO for {@link TitleauthorId}
 */
@Value
public class TitleauthorIdDto implements Serializable {
    String auId;
    String titleId;
}