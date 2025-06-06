package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Publisher;

import java.io.Serializable;

/**
 * DTO for {@link Publisher}
 */
@Value
public class PublisherDto implements Serializable {
    String pubId;
    String pubName;
    String city;
    String state;
    String country;
}