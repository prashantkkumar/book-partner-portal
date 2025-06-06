package org.example.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.TitleDto;
import org.example.backend.entities.Title;
import org.example.backend.mapper.TitleMapper;
import org.example.backend.repository.TitleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
@RequiredArgsConstructor
public class TitleController {

    private final TitleRepository titleRepository;
    private final TitleMapper titleMapper;

    // GET all titles
    @GetMapping
    public List<TitleDto> getAllTitles() {
        return titleRepository.findAll()
                .stream()
                .map(titleMapper::toDto)
                .toList();
    }

    // GET title by ID
    @GetMapping("/{titleId}")
    public ResponseEntity<TitleDto> getTitleById(@PathVariable String titleId) {
        return titleRepository.findById(titleId)
                .map(titleMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new title
    @PostMapping
    public ResponseEntity<TitleDto> createTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.toEntity(titleDto);
        Title savedTitle = titleRepository.save(title);
        return ResponseEntity.ok(titleMapper.toDto(savedTitle));
    }

    // PUT update title by ID
    @PutMapping("/{titleId}")
    public ResponseEntity<TitleDto> updateTitle(@PathVariable String titleId, @RequestBody TitleDto titleDto) {
        return titleRepository.findById(titleId)
                .map(existing -> {
                    titleMapper.partialUpdate(titleDto, existing);
                    Title updated = titleRepository.save(existing);
                    return ResponseEntity.ok(titleMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
