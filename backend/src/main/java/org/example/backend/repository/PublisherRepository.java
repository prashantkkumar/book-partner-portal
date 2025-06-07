package org.example.backend.repository;
import org.example.backend.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, String> {
    List<Publisher> findByCity(String city);
    List<Publisher> findByCountry(String country);
}
