package org.example.backend.repository;

import org.example.backend.entities.Discount;
import org.example.backend.entities.DiscountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, DiscountId> {
    Optional<Discount> findById_DiscounttypeAndId_StorId(String discounttype, String storId);
}
