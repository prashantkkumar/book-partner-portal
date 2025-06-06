package org.example.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class TitleauthorId implements Serializable {
    private static final long serialVersionUID = -8911362440573980083L;
    @Column(name = "au_id", nullable = false, length = 11)
    private String auId;

    @Column(name = "title_id", nullable = false, length = 10)
    private String titleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TitleauthorId entity = (TitleauthorId) o;
        return Objects.equals(this.auId, entity.auId) &&
                Objects.equals(this.titleId, entity.titleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auId, titleId);
    }

}