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
@Table(name = "jobs")
public class Job {
    @Id
    @Column(name = "job_id", nullable = false)
    private Short id;

    @ColumnDefault("'New Position - title not formalized yet'")
    @Column(name = "job_desc", nullable = false, length = 50)
    private String jobDesc;

    @Column(name = "min_lvl", nullable = false)
    private Integer minLvl;

    @Column(name = "max_lvl", nullable = false)
    private Integer maxLvl;

}