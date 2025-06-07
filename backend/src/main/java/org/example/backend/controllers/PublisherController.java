package org.example.backend.controllers;
import lombok.RequiredArgsConstructor;
import org.example.backend.dto.PublisherDto;
import org.example.backend.entities.Publisher;
import org.example.backend.mapper.PublisherMapper;
import org.example.backend.repository.PublisherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @GetMapping
    public List<PublisherDto> getAll() {
        return publisherRepository.findAll().stream()
                .map(publisherMapper::toDto)
                .toList();
    }

    @GetMapping("/{pub_id}")
    public ResponseEntity<PublisherDto> getById(@PathVariable String pub_id) {
        return publisherRepository.findById(pub_id)
                .map(publisherMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/city/{city}")
    public List<PublisherDto> getByCity(@PathVariable String city) {
        return publisherRepository.findByCity(city).stream()
                .map(publisherMapper::toDto)
                .toList();
    }

    @GetMapping("/country/{country}")
    public List<PublisherDto> getByCountry(@PathVariable String country) {
        return publisherRepository.findByCountry(country).stream()
                .map(publisherMapper::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<PublisherDto> addPublisher(@RequestBody PublisherDto dto) {
        Publisher entity = publisherMapper.toEntity(dto);
        Publisher saved = publisherRepository.save(entity);
        return ResponseEntity.ok(publisherMapper.toDto(saved));
    }

    @PutMapping("/{pub_id}")
    public ResponseEntity<PublisherDto> updatePublisher(@PathVariable String pub_id, @RequestBody PublisherDto dto) {
        return publisherRepository.findById(pub_id)
                .map(existing -> {
                    publisherMapper.partialUpdate(dto, existing);
                    Publisher updated = publisherRepository.save(existing);
                    return ResponseEntity.ok(publisherMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
