package org.example.backend.repository;

import org.example.backend.entities.Titleauthor;
import org.example.backend.entities.TitleauthorId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitleauthorRepository extends JpaRepository<Titleauthor, TitleauthorId> {
    List<Titleauthor> findByAu_AuId(String auId);
}
