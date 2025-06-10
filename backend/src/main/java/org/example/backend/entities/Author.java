package org.example.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @Column(name = "au_id", nullable = false, length = 11)
    private String auId;

    @Column(name = "au_lname", nullable = false, length = 40)
    private String auLname;

    @Column(name = "au_fname", nullable = false, length = 20)
    private String auFname;

    @ColumnDefault("'UNKNOWN'")
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;

    @Column(name = "address", length = 40)
    private String address;

    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "state", length = 2)
    private String state;

    @Column(name = "zip", length = 5)
    private String zip;

    @Column(name = "contract", nullable = false)
    private Integer contract;

    @OneToMany(mappedBy = "au", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Titleauthor> titleauthors = new ArrayList<>();
}