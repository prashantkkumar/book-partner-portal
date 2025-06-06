package org.example.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "emp_id", nullable = false, length = 10)
    private String empId;

    @Column(name = "fname", nullable = false, length = 20)
    private String fname;

    @Column(name = "minit")
    private Character minit;

    @Column(name = "lname", nullable = false, length = 30)
    private String lname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("1")
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ColumnDefault("10")
    @Column(name = "job_lvl")
    private Integer jobLvl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("'9952'")
    @JoinColumn(name = "pub_id", nullable = false)
    private Publisher pub;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "hire_date", nullable = false)
    private Instant hireDate;

}