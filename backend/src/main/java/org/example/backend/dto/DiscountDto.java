package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Discount;
import org.example.backend.entities.DiscountId;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Discount}
 */
@Value
public class DiscountDto implements Serializable {
    DiscountId id;
    Short lowqty;
    Short highqty;
    BigDecimal discount;
}