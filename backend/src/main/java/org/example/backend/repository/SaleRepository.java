package org.example.backend.repository;

import org.example.backend.entities.Sale;
import org.example.backend.entities.SaleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, SaleId> {

    // Find all sales by store ID
    List<Sale> findByStor_StorId(String storId);

    // Find all sales by title ID
    List<Sale> findByTitle_TitleId(String titleId);

    // Find all sales by order date
    List<Sale> findByOrdDate(Instant ordDate);
}
