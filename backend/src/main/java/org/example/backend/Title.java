package org.example.backend;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "titles")
public class Title {
    @Id
    @Column(name = "title_id", nullable = false, length = 10)
    private String titleId;

    @Column(name = "title", nullable = false, length = 80)
    private String title;

    @ColumnDefault("'UNDECIDED'")
    @Column(name = "type", nullable = false, length = 12)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pub_id")
    private Publisher pub;

    @Column(name = "price")
    private Double price;

    @Column(name = "advance")
    private Double advance;

    @Column(name = "royalty")
    private Integer royalty;

    @Column(name = "ytd_sales")
    private Integer ytdSales;

    @Column(name = "notes", length = 200)
    private String notes;

    @Column(name = "pubdate", nullable = false)
    private Instant pubdate;

}