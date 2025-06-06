package org.example.backend;

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

}