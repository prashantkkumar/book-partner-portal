package org.example.backend.controllers;
import lombok.RequiredArgsConstructor;
import org.example.backend.dto.JobDto;
import org.example.backend.entities.Job;
import org.example.backend.mapper.JobMapper;
import org.example.backend.repository.JobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @GetMapping
    public List<JobDto> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(jobMapper::toDto)
                .toList();
    }

    @GetMapping("/{job_id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Short job_id) {
        return jobRepository.findById(job_id)
                .map(jobMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JobDto> addJob(@RequestBody JobDto dto) {
        Job job = jobMapper.toEntity(dto);
        Job saved = jobRepository.save(job);
        return ResponseEntity.ok(jobMapper.toDto(saved));
    }

//    @PutMapping("/{job_id}")
//    public ResponseEntity<JobDto> updateJob(@PathVariable Short job_id, @RequestBody JobDto dto) {
//        return jobRepository.findById(job_id)
//                .map(existing -> {
//                    jobMapper.partialUpdate(dto, existing);
//                    Job updated = jobRepository.save(existing);
//                    return ResponseEntity.ok(jobMapper.toDto(updated));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
}

