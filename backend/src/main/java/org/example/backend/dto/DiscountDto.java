package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Discount;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Discount}
 */
@Value
public class DiscountDto implements Serializable {
    Long id;
    String discounttype;
    Short lowqty;
    Short highqty;
    BigDecimal discount;
}