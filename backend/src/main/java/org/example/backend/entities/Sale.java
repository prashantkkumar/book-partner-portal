package org.example.backend.entities;

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
    private Store stor;

    @MapsId("titleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;

    // ✅ Add this field and map to ord_num
    @Column(name = "ord_num", nullable = false, length = 11, insertable = false, updatable = false)
    private String ordNum;

    @Column(name = "ord_date", nullable = false)
    private Instant ordDate;

    @Column(name = "qty", nullable = false)
    private Short qty;

    @Column(name = "payterms", nullable = false, length = 12)
    private String payterms;

}