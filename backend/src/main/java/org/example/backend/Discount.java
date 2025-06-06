package org.example.backend;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "discounts")
public class Discount {
    @Column(name = "discounttype", nullable = false, length = 40)
    private String discounttype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stor_id")
    private org.example.backend.Store stor;

    @Column(name = "lowqty")
    private Short lowqty;

    @Column(name = "highqty")
    private Short highqty;

    @Column(name = "discount", nullable = false, precision = 4, scale = 2)
    private BigDecimal discount;

}