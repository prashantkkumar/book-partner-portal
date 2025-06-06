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
public class SaleId implements Serializable {
    private static final long serialVersionUID = -3127680085116487113L;
    @Column(name = "stor_id", nullable = false, length = 4)
    private String storId;

    @Column(name = "ord_num", nullable = false, length = 20)
    private String ordNum;

    @Column(name = "title_id", nullable = false, length = 10)
    private String titleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SaleId entity = (SaleId) o;
        return Objects.equals(this.titleId, entity.titleId) &&
                Objects.equals(this.ordNum, entity.ordNum) &&
                Objects.equals(this.storId, entity.storId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleId, ordNum, storId);
    }

}