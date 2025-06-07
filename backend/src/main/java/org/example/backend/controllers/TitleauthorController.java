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

    //Get all titles by an author
    @GetMapping("/{auId}")
    public ResponseEntity<List<TitleauthorDto>> getTitlesByAuthor(@PathVariable String auId) {
        if (!authorRepository.existsById(auId)) {
            return ResponseEntity.notFound().build();
        }

        List<Titleauthor> list = titleauthorRepository.findByAu_AuId(auId);
        List<TitleauthorDto> dtoList = list.stream()
                .map(titleauthorMapper::toDto)
                .toList(); // or .collect(Collectors.toList()) if using Java <16

        return ResponseEntity.ok(dtoList);
    }



    // POST Map the Author By Title
    @PostMapping
    public ResponseEntity<Titleauthor> assignTitleToAuthor(
            @RequestBody TitleauthorPayload payload
    ) {
        String auId = payload.getIdDto().getAuId();
        String titleId = payload.getIdDto().getTitleId();

        if (!authorRepository.existsById(auId) || !titleRepository.existsById(titleId)) {
            return ResponseEntity.badRequest().build();
        }

        TitleauthorId id = new TitleauthorId();
        id.setAuId(auId);
        id.setTitleId(titleId);

        Titleauthor entity = titleauthorMapper.toEntity(payload.getDto(), payload.getIdDto());
        entity.setId(id);

        Author auEntity = authorRepository.getReferenceById(auId);
        Title titleEntity = titleRepository.getReferenceById(titleId);
        entity.setAu(auEntity);
        entity.setTitle(titleEntity);

        Titleauthor saved = titleauthorRepository.save(entity);
        return ResponseEntity.ok(saved);
    }

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
