package org.example.backend.repository;

import org.example.backend.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,String> {
    List<Store> findByCity(String city);
    List<Store> findByState(String state);
    List<Store> findByZip(String zip);
}
