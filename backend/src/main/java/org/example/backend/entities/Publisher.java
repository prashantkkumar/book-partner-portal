package org.example.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @Column(name = "pub_id", nullable = false, length = 4)
    private String pubId;

    @Column(name = "pub_name", length = 40)
    private String pubName;

    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "state", length = 2)
    private String state;

    @ColumnDefault("'USA'")
    @Column(name = "country", length = 30)
    private String country;

}