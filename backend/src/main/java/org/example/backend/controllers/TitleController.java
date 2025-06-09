package org.example.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.RoyschedDto;
import org.example.backend.dto.TitleDto;
import org.example.backend.entities.Roysched;
import org.example.backend.entities.Title;
import org.example.backend.mapper.TitleMapper;
import org.example.backend.repository.RoyschedRepository;
import org.example.backend.repository.TitleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
@RequiredArgsConstructor
public class TitleController {

    private final TitleRepository titleRepository;
    private final RoyschedRepository royschedRepository;
    private final TitleMapper titleMapper;

    // ------------------------ Title Endpoints ------------------------

    @GetMapping
    public List<TitleDto> getAllTitles() {
        return titleRepository.findAll()
                .stream()
                .map(titleMapper::toDto)
                .toList();
    }

    @GetMapping("/{titleId}")
    public ResponseEntity<TitleDto> getTitleById(@PathVariable String titleId) {
        return titleRepository.findById(titleId)
                .map(titleMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TitleDto> createTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.toEntity(titleDto);
        Title savedTitle = titleRepository.save(title);
        return ResponseEntity.ok(titleMapper.toDto(savedTitle));
    }

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

    @GetMapping("/type/{type}")
    public List<TitleDto> getTitlesByType(@PathVariable String type) {
        return titleRepository.findByTypeIgnoreCase(type)
                .stream()
                .map(titleMapper::toDto)
                .toList();
    }

    @GetMapping("/pub/{pubId}")
    public List<TitleDto> getTitlesByPublisher(@PathVariable String pubId) {
        return titleRepository.findByPub_PubId(pubId)
                .stream()
                .map(titleMapper::toDto)
                .toList();
    }

    // ------------------------ Roysched Endpoints under Title ------------------------

    // Get royalty schedule for a title
    @GetMapping("/{titleId}/roysched")
    public ResponseEntity<List<RoyschedDto>> getRoyschedByTitle(@PathVariable String titleId) {
        if (!titleRepository.existsById(titleId)) {
            return ResponseEntity.notFound().build();
        }

        List<RoyschedDto> royscheds = royschedRepository.findByTitle_TitleId(titleId)
                .stream()
                .map(titleMapper::toDto)
                .toList();

        return ResponseEntity.ok(royscheds);
    }

    // Add royalty schedule
    @PostMapping("/{titleId}/roysched")
    public ResponseEntity<RoyschedDto> addRoyschedToTitle(@PathVariable String titleId,
                                                          @RequestBody RoyschedDto dto) {
        return titleRepository.findById(titleId)
                .map(title -> {
                    Roysched entity = titleMapper.toEntity(dto);
                    entity.setTitle(title); // link to title
                    Roysched saved = royschedRepository.save(entity);
                    return ResponseEntity.ok(titleMapper.toDto(saved));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Update royalty schedule
    @PutMapping("/roysched/{royschedId}")
    public ResponseEntity<RoyschedDto> updateRoysched(@PathVariable Integer royschedId,
                                                      @RequestBody RoyschedDto dto) {
        return royschedRepository.findById(royschedId)
                .map(existing -> {
                    titleMapper.partialUpdate(dto, existing);
                    Roysched updated = royschedRepository.save(existing);
                    return ResponseEntity.ok(titleMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Final Testing1";
    }
}
