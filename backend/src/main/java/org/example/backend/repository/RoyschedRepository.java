package org.example.backend.repository;


import org.example.backend.entities.Roysched;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoyschedRepository extends JpaRepository<Roysched, Long> {
    List<Roysched> findByTitleTitleId(String titleId);
}
