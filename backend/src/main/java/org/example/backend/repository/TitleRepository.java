package org.example.backend.repository;

import org.example.backend.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, String> {
    List<Title> findByType(String type);
    List<Title> findByPublisherPubId(String pubId);
}
