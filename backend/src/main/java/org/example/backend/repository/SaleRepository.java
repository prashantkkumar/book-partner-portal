package org.example.backend.repository;

import org.example.backend.entities.Sale;
import org.example.backend.entities.SaleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, SaleId> {

//    @Query(value = """
//        SELECT t.title AS title,
//               DATE_FORMAT(s.ord_date, '%Y-%m') AS month,
//               SUM(s.qty) AS totalQty
//        FROM sales s
//        JOIN titles t ON s.title_id = t.title_id
//        GROUP BY t.title, month
//        ORDER BY t.title, month
//        """, nativeQuery = true)
//    List<TitleMonthlySales> getTitleMonthlySalesSummary();

    // Native query for monthly sales per title and store
//    @Query(value = """
//    SELECT t.title AS title,
//           st.stor_name AS store,
//           DATE_FORMAT(s.ord_date, '%Y-%m') AS month,
//           SUM(s.qty) AS totalQty
//    FROM sales s
//    JOIN titles t ON s.title_id = t.title_id
//    JOIN stores st ON s.stor_id = st.stor_id
//    GROUP BY t.title, st.stor_name, month
//    ORDER BY t.title, st.stor_name, month
//    """, nativeQuery = true)

    // JPQL query for monthly sales per title and store
    @Query("""
    SELECT t.title AS title, 
           st.storName AS store, 
           FUNCTION('DATE_FORMAT', s.ordDate, '%Y-%m') AS month, 
           SUM(s.qty) AS totalQty 
    FROM Sale s 
    JOIN s.title t 
    JOIN s.stor st 
    GROUP BY t.title, st.storName, FUNCTION('DATE_FORMAT', s.ordDate, '%Y-%m') 
    ORDER BY t.title ASC, st.storName ASC, month ASC
""")

    List<TitleMonthlySales> getAllTitlesMonthlySalesPerStore();


    // Find all sales by store ID
    List<Sale> findByStor_StorId(String storId);

    // Find all sales by title ID
    List<Sale> findByTitle_TitleId(String titleId);

    // Find all sales by order date
    List<Sale> findByOrdDate(Instant ordDate);
}
