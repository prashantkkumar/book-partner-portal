package org.example.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.TitleauthorDto;
import org.example.backend.dto.TitleauthorIdDto;
import org.example.backend.dto.TitleauthorPayload;
import org.example.backend.entities.Author;
import org.example.backend.entities.Title;
import org.example.backend.entities.Titleauthor;
import org.example.backend.entities.TitleauthorId;
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
    private final AuthorRepository authorRepository;
    private final TitleRepository titleRepository;

    // Get all titles by an author
    @GetMapping("/{auId}")
    public ResponseEntity<List<TitleauthorDto>> getTitlesByAuthor(@PathVariable String auId) {
        if (!authorRepository.existsById(auId)) {
            return ResponseEntity.notFound().build();
        }

        List<Titleauthor> list = titleauthorRepository.findByAu_AuId(auId);
        List<TitleauthorDto> dtoList = list.stream()
                .map(titleauthor -> {
                    TitleauthorDto dto = new TitleauthorDto();
                    dto.setAuOrd(titleauthor.getAuOrd());
                    dto.setRoyaltyper(titleauthor.getRoyaltyper());
                    dto.setAuthorName(titleauthor.getAu().getAuLname());
                    dto.setTitleName(titleauthor.getTitle().getTitle());
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    // POST: Map the Author By Title
    @PostMapping
    public ResponseEntity<Titleauthor> assignTitleToAuthor(@RequestBody TitleauthorPayload payload) {
        try {
            String auId = payload.getIdDto().getAuId();
            String titleId = payload.getIdDto().getTitleId();

            if (!authorRepository.existsById(auId) || !titleRepository.existsById(titleId)) {
                return ResponseEntity.badRequest().body(null);
            }

            TitleauthorId id = new TitleauthorId();
            id.setAuId(auId);
            id.setTitleId(titleId);

            Titleauthor entity = new Titleauthor();
            entity.setId(id);
            entity.setAuOrd(payload.getDto().getAuOrd());
            entity.setRoyaltyper(payload.getDto().getRoyaltyper());

            Author author = authorRepository.getReferenceById(auId);
            Title title = titleRepository.getReferenceById(titleId);
            entity.setAu(author);
            entity.setTitle(title);

            Titleauthor saved = titleauthorRepository.save(entity);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
