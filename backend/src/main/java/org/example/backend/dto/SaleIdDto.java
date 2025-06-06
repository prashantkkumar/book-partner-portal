package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.SaleId;

import java.io.Serializable;

/**
 * DTO for {@link SaleId}
 */
@Value
public class SaleIdDto implements Serializable {
    String storId;
    String ordNum;
    String titleId;
}