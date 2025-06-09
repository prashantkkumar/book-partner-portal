package org.example.backend.repository;

import org.example.backend.dto.AuthorBookDto;
import org.example.backend.entities.Titleauthor;
import org.example.backend.entities.TitleauthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TitleauthorRepository extends JpaRepository<Titleauthor, TitleauthorId> {
    List<Titleauthor> findByAu_AuId(String auId);

    @Query("SELECT new org.example.backend.dto.AuthorBookDto(" +
            "ta.au.auId, ta.au.auFname, ta.au.auLname, ta.title.title) " +
            "FROM Titleauthor ta")
    List<AuthorBookDto> fetchAuthorBooks();

}
