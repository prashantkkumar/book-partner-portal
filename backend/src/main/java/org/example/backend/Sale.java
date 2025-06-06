package org.example.backend;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale {
    @EmbeddedId
    private SaleId id;

    @MapsId("storId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stor_id", nullable = false)
    private org.example.backend.Store stor;

    @MapsId("titleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "title_id", nullable = false)
    private org.example.backend.Title title;

    @Column(name = "ord_date", nullable = false)
    private Instant ordDate;

    @Column(name = "qty", nullable = false)
    private Short qty;

    @Column(name = "payterms", nullable = false, length = 12)
    private String payterms;

}