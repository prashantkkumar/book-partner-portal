package org.example.backend.repository;

import org.example.backend.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Optional<Discount> findByStor_StorIdAndDiscounttype(String storId, String discounttype);
}
