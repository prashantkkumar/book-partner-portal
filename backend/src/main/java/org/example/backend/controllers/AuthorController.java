package org.example.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.AuthorDto;
import org.example.backend.entities.Author;
import org.example.backend.mapper.AuthorMapper;
import org.example.backend.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    // GET All Authors
    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    // POST An Author
    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto dto) {
        Author saved = authorRepository.save(authorMapper.toEntity(dto));
        return ResponseEntity.ok(authorMapper.toDto(saved));
    }

    // PUT Any Author
    @PutMapping("/{auId}")
    public ResponseEntity<AuthorDto> updateAuthor(
            @PathVariable String auId,
            @RequestBody AuthorDto dto
    ) {
        return authorRepository.findById(auId)
                .map(existing -> {
                    authorMapper.partialUpdate(dto, existing);
                    Author updated = authorRepository.save(existing);
                    return ResponseEntity.ok(authorMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // GET Author By Id
    @GetMapping("/{auId}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable String auId) {
        return authorRepository.findById(auId)
                .map(authorMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET Author By LastName
    @GetMapping("/lname/{lname}")
    public List<AuthorDto> getByLastName(@PathVariable String lname) {
        return authorRepository.findByAuLname(lname)
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    // GET Author by FirstName
    @GetMapping("/fname/{fname}")
    public List<AuthorDto> getByFirstName(@PathVariable String fname) {
        return authorRepository.findByAuFname(fname)
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    // GET Author By PhoneNumber
    @GetMapping("/phone/{phone}")
    public List<AuthorDto> getByPhone(@PathVariable String phone) {
        return authorRepository.findByPhone(phone)
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    // GET Author By ZipCode
    @GetMapping("/zip/{zip}")
    public List<AuthorDto> getByZip(@PathVariable String zip) {
        return authorRepository.findByZip(zip)
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    // GET Author By State
    @GetMapping("/state/{state}")
    public List<AuthorDto> getByState(@PathVariable String state) {
        return authorRepository.findByState(state)
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    // GET Author By City
    @GetMapping("/city/{city}")
    public List<AuthorDto> getByCity(@PathVariable String city) {
        return authorRepository.findByCity(city)
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }
}
