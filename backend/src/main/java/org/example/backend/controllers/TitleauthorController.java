package org.example.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.TitleauthorDto;
import org.example.backend.dto.TitleauthorIdDto;
import org.example.backend.entities.Author;
import org.example.backend.entities.Title;
import org.example.backend.entities.Titleauthor;
import org.example.backend.entities.TitleauthorId;
import org.example.backend.mapper.TitleauthorMapper;
import org.example.backend.repository.AuthorRepository;
import org.example.backend.repository.TitleRepository;
import org.example.backend.repository.TitleauthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titleauthor")
@RequiredArgsConstructor
public class TitleauthorController {

    private final TitleauthorRepository titleauthorRepository;
    private final TitleauthorMapper titleauthorMapper;
    private final AuthorRepository authorRepository;
    private final TitleRepository titleRepository;

    // GET /api/titleauthor/{auId}  – Get all titles by an author
    @GetMapping("/{auId}")
    public ResponseEntity<List<Titleauthor>> getTitlesByAuthor(@PathVariable String auId) {
        if (!authorRepository.existsById(auId)) {
            return ResponseEntity.notFound().build();
        }
        List<Titleauthor> list = titleauthorRepository.findByAu_AuId(auId);
        return ResponseEntity.ok(list);
    }


    @PostMapping
    public ResponseEntity<Titleauthor> assignTitleToAuthor(
            @RequestBody TitleauthorPayload payload
    ) {
        String auId = payload.getIdDto().getAuId();
        String titleId = payload.getIdDto().getTitleId();

        // 1) Must have both author and title exist
        if (!authorRepository.existsById(auId) || !titleRepository.existsById(titleId)) {
            return ResponseEntity.badRequest().build();
        }

        // 2) Build the composite‐key object & entity
        TitleauthorId id = new TitleauthorId();
        id.setAuId(auId);
        id.setTitleId(titleId);

        Titleauthor entity = titleauthorMapper.toEntity(payload.getDto(), payload.getIdDto());
        entity.setId(id);

        // 3) Ensure the “au” and “title” references are populated (MapStruct @Mapping took care of it)
        Author auEntity = authorRepository.getReferenceById(auId);
        Title titleEntity = titleRepository.getReferenceById(titleId);
        entity.setAu(auEntity);
        entity.setTitle(titleEntity);

        Titleauthor saved = titleauthorRepository.save(entity);
        return ResponseEntity.ok(saved);
    }

    // Inner static class just to bundle the two DTOs (idDto + dataDto).
    private static class TitleauthorPayload {
        private TitleauthorIdDto idDto;
        private TitleauthorDto dto;

        public TitleauthorIdDto getIdDto() {
            return idDto;
        }

        public void setIdDto(TitleauthorIdDto idDto) {
            this.idDto = idDto;
        }

        public TitleauthorDto getDto() {
            return dto;
        }

        public void setDto(TitleauthorDto dto) {
            this.dto = dto;
        }
    }
}
