package org.example.backend.repository;

import org.example.backend.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, String> {
    List<Author> findByAuLname(String auLname);
    List<Author> findByAuFname(String auFname);
    List<Author> findByPhone(String phone);
    List<Author> findByZip(String zip);
    List<Author> findByState(String state);
    List<Author> findByCity(String city);
}
