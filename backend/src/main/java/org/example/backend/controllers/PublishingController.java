package org.example.backend.controllers;


import org.example.backend.entities.Author;
import org.example.backend.entities.Publisher;
import org.example.backend.entities.Title;
import org.example.backend.service.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publishing")
public class PublishingController {

    @Autowired
    private PublishingService publishingService;

    @PostMapping("/title-with-author")
    public ResponseEntity<String> addTitleWithAuthor(
            @RequestBody PublishingRequest request) {
        try {
            publishingService.addTitleWithAuthor(
                    request.getTitle(),
                    request.getAuthor(),
                    request.getPublisher(),
                    request.getAuOrd(),
                    request.getRoyaltyper()
            );
            return ResponseEntity.ok("Title and Author added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/author-with-title")
    public ResponseEntity<String> addAuthorWithTitle(
            @RequestBody PublishingRequest request) {
        try {
            publishingService.addAuthorWithTitle(
                    request.getAuthor(),
                    request.getTitle(),
                    request.getPublisher(),
                    request.getAuOrd(),
                    request.getRoyaltyper()
            );
            return ResponseEntity.ok("Author and Title added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DTO to hold request data
    public static class PublishingRequest {
        private Title title;
        private Author author;
        private Publisher publisher;
        private Byte auOrd;
        private Integer royaltyper;

        public Title getTitle() {
            return title;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Publisher getPublisher() {
            return publisher;
        }

        public void setPublisher(Publisher publisher) {
            this.publisher = publisher;
        }

        public Byte getAuOrd() {
            return auOrd;
        }

        public void setAuOrd(Byte auOrd) {
            this.auOrd = auOrd;
        }

        public Integer getRoyaltyper() {
            return royaltyper;
        }

        public void setRoyaltyper(Integer royaltyper) {
            this.royaltyper = royaltyper;
        }
    }
}
