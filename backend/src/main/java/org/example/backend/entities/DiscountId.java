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
public class DiscountId implements Serializable {
    private static final long serialVersionUID = -2189450742524061090L;
    @Column(name = "discounttype", nullable = false, length = 40)
    private String discounttype;

    @Column(name = "stor_id", length = 4)
    private String storId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DiscountId entity = (DiscountId) o;
        return Objects.equals(this.discounttype, entity.discounttype) &&
                Objects.equals(this.storId, entity.storId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discounttype, storId);
    }

}