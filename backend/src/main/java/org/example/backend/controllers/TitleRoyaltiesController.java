package org.example.backend.controllers;


import lombok.RequiredArgsConstructor;
import org.example.backend.entities.Roysched;
import org.example.backend.entities.Title;
import org.example.backend.repository.RoyschedRepository;
import org.example.backend.repository.TitleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TitleRoyaltiesController {

    private final TitleRepository titleRepository;
    private final RoyschedRepository royschedRepository;

    // ----------- Titles Endpoints -----------

    @GetMapping("/titles")
    public ResponseEntity<List<Title>> getAllTitles() {
        return ResponseEntity.ok(titleRepository.findAll());
    }

    @PostMapping("/titles")
    public ResponseEntity<Title> addTitle(@RequestBody Title title) {
        return ResponseEntity.ok(titleRepository.save(title));
    }

    @PutMapping("/titles/{titleId}")
    public ResponseEntity<Title> updateTitle(@PathVariable String titleId, @RequestBody Title title) {
        title.setTitleId(titleId);
        return ResponseEntity.ok(titleRepository.save(title));
    }

    @GetMapping("/titles/{titleId}")
    public ResponseEntity<Title> getTitleById(@PathVariable String titleId) {
        return titleRepository.findById(titleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titles/type/{type}")
    public ResponseEntity<List<Title>> getTitlesByType(@PathVariable String type) {
        return ResponseEntity.ok(titleRepository.findByType(type));
    }

    @GetMapping("/titles/pub/{pubId}")
    public ResponseEntity<List<Title>> getTitlesByPublisher(@PathVariable String pubId) {
        return ResponseEntity.ok(titleRepository.findByPublisherPubId(pubId));
    }

    // ----------- Roysched Endpoints -----------

    @GetMapping("/roysched/{titleId}")
    public ResponseEntity<List<Roysched>> getRoyschedByTitleId(@PathVariable String titleId) {
        return ResponseEntity.ok(royschedRepository.findByTitleTitleId(titleId));
    }

    @PostMapping("/roysched")
    public ResponseEntity<Roysched> addRoysched(@RequestBody Roysched roysched) {
        return ResponseEntity.ok(royschedRepository.save(roysched));
    }

//    @PutMapping("/roysched/{royschedId}")
//    public ResponseEntity<Roysched> updateRoysched(@PathVariable Long royschedId, @RequestBody Roysched roysched) {
//        roysched.setId(royschedId);
//        return ResponseEntity.ok(royschedRepository.save(roysched));
//    }
}

