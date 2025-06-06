package org.example.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "titleauthor")
public class Titleauthor {
    @EmbeddedId
    private TitleauthorId id;

    @MapsId("auId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "au_id", nullable = false)
    private Author au;

    @MapsId("titleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;

    @Column(name = "au_ord")
    private Byte auOrd;

    @Column(name = "royaltyper")
    private Integer royaltyper;

}