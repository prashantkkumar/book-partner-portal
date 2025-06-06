package org.example.backend.repository;

import org.example.backend.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TitleRepository extends JpaRepository<Title, String> {
    List<Title> findByType(String type);
    List<Title> findByPub_PubId(String pubId); //
    List<Title> findByTypeIgnoreCase(String type);

}

