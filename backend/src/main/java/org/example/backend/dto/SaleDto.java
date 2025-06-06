package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Sale;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Sale}
 */
@Value
public class SaleDto implements Serializable {
    Instant ordDate;
    Short qty;
    String payterms;
}