package org.example.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.AuthorBookDto;
import org.example.backend.dto.AuthorTitleCountDto;
import org.example.backend.dto.AuthorWithBooksDto;
import org.example.backend.dto.TitleauthorDto;
import org.example.backend.entities.Titleauthor;
import org.example.backend.entities.TitleauthorId;
import org.example.backend.mapper.TitleauthorMapper;
import org.example.backend.repository.AuthorRepository;
import org.example.backend.repository.TitleRepository;
import org.example.backend.repository.TitleauthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/titleauthor")
@RequiredArgsConstructor
public class TitleauthorController {

    private final TitleauthorRepository titleauthorRepository;
    private final AuthorRepository authorRepository;
    private final TitleRepository titleRepository;
    private final TitleauthorMapper titleauthorMapper;

    @GetMapping("/author-title-counts")
    public List<AuthorTitleCountDto> getAuthorTitleCounts() {
        return titleauthorRepository.fetchAuthorTitleCounts();
    }

    // Get all Authors Of Book/Title
    @GetMapping
    public ResponseEntity<List<AuthorWithBooksDto>> getGroupedAuthorBooks() {
        List<AuthorBookDto> flatList = titleauthorRepository.fetchAuthorBooks();

        Map<String, AuthorWithBooksDto> groupedMap = new LinkedHashMap<>();

        for (AuthorBookDto dto : flatList) {
            groupedMap.computeIfAbsent(dto.getAuId(), id ->
                    new AuthorWithBooksDto(dto.getAuId(), dto.getAuFname(), dto.getAuLname(), new ArrayList<>())
            );
            groupedMap.get(dto.getAuId()).getTitles().add(dto.getTitle());
        }

        List<AuthorWithBooksDto> groupedList = new ArrayList<>(groupedMap.values());
        return ResponseEntity.ok(groupedList);
    }


    // GET titles by authorid
    @GetMapping("/{auId}")
    public ResponseEntity<List<TitleauthorDto>> getTitlesByAuthor(@PathVariable String auId) {
        if (!authorRepository.existsById(auId)) {
            return ResponseEntity.notFound().build();
        }

        List<Titleauthor> list = titleauthorRepository.findByAu_AuId(auId);
        List<TitleauthorDto> dtoList = list.stream()
                .map(titleauthorMapper::toDto)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    //Get full detail
    @GetMapping("/detailed")
    public ResponseEntity<List<TitleauthorDto>> getAllMappings() {
        List<Titleauthor> allMappings = titleauthorRepository.findAll();
        List<TitleauthorDto> dtoList = allMappings.stream()
                .map(titleauthorMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    // POST assign title to author
    @PostMapping
    public ResponseEntity<TitleauthorDto> assignTitleToAuthor(@RequestBody TitleauthorDto dto) {
        String auId = dto.getId().getAuId();
        String titleId = dto.getId().getTitleId();

        if (!authorRepository.existsById(auId) || !titleRepository.existsById(titleId)) {
            return ResponseEntity.badRequest().build();
        }

        Titleauthor entity = titleauthorMapper.toEntity(dto);
        entity.setAu(authorRepository.getReferenceById(auId));
        entity.setTitle(titleRepository.getReferenceById(titleId));

        Titleauthor saved = titleauthorRepository.save(entity);
        return ResponseEntity.ok(titleauthorMapper.toDto(saved));
    }

    // PUT update mapping
    @PutMapping
    public ResponseEntity<TitleauthorDto> updateTitleAuthor(@RequestBody TitleauthorDto dto) {
        TitleauthorId id = new TitleauthorId();
        id.setAuId(dto.getId().getAuId());
        id.setTitleId(dto.getId().getTitleId());

        return titleauthorRepository.findById(id)
                .map(existing -> {
                    titleauthorMapper.partialUpdate(dto, existing);
                    Titleauthor updated = titleauthorRepository.save(existing);
                    return ResponseEntity.ok(titleauthorMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
