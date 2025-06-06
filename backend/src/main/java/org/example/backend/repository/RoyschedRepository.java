package org.example.backend.repository;

import org.example.backend.entities.Roysched;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoyschedRepository extends JpaRepository<Roysched, Integer> {
    List<Roysched> findByTitle_TitleId(String titleId);
}
