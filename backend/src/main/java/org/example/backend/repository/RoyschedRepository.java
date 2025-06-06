package org.example.backend.repository;


import org.example.backend.entities.Roysched;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoyschedRepository extends JpaRepository<Roysched, Long> {
    List<Roysched> findByTitleTitleId(String titleId);
}
