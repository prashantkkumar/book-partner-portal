package org.example.backend.service;

import org.example.backend.entities.*;
import org.example.backend.repository.AuthorRepository;
import org.example.backend.repository.PublisherRepository;
import org.example.backend.repository.TitleRepository;
import org.example.backend.repository.TitleauthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class PublishingService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private TitleauthorRepository titleauthorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Transactional
    public void addTitleWithAuthor(Title title, Author author, Publisher publisher, Byte auOrd, Integer royaltyper) {
        // Validate required fields
        if (author.getAuId() == null || author.getAuLname() == null || author.getAuFname() == null) {
            throw new IllegalArgumentException("Author ID, last name, and first name are required");
        }
        if (title.getTitleId() == null || title.getTitle() == null) {
            throw new IllegalArgumentException("Title ID and title name are required");
        }

        // Resolve Author (use a new variable to avoid reassigning 'author')
        Author resolvedAuthor = authorRepository.findById(author.getAuId()).orElse(null);
        if (resolvedAuthor == null) {
            // Check for existing Author by name to avoid duplicates
            List<Author> authorsByName = authorRepository.findByAuLname(author.getAuLname());
            authorsByName.removeIf(a -> !a.getAuFname().equals(author.getAuFname())); // 'author' is effectively final
            if (!authorsByName.isEmpty()) {
                resolvedAuthor = authorsByName.get(0); // Reuse first matching author
            }
        }
        if (resolvedAuthor == null) {
            // Save new Author
            resolvedAuthor = author; // Use input author
            if (resolvedAuthor.getPhone() == null) resolvedAuthor.setPhone("UNKNOWN");
            if (resolvedAuthor.getContract() == null) resolvedAuthor.setContract(0);
            authorRepository.save(resolvedAuthor);
        }

        // Handle Publisher (optional)
        if (publisher != null && publisher.getPubId() != null) {
            Publisher existingPublisher = publisherRepository.findById(publisher.getPubId()).orElse(null);
            if (existingPublisher == null) {
                if (publisher.getCountry() == null) publisher.setCountry("USA");
                publisherRepository.save(publisher);
            } else {
                publisher = existingPublisher;
            }
            title.setPub(publisher);
        }

        // Resolve Title
        Title resolvedTitle = titleRepository.findById(title.getTitleId()).orElse(null);
        if (resolvedTitle == null) {
            resolvedTitle = title;
            if (resolvedTitle.getType() == null) resolvedTitle.setType("UNDECIDED");
            if (resolvedTitle.getPubdate() == null) resolvedTitle.setPubdate(Instant.now());
            titleRepository.save(resolvedTitle);
        }

        // Create Titleauthor relationship
        Titleauthor titleauthor = new Titleauthor();
        TitleauthorId id = new TitleauthorId();
        id.setAuId(resolvedAuthor.getAuId());
        id.setTitleId(resolvedTitle.getTitleId());
        titleauthor.setId(id);
        titleauthor.setAu(resolvedAuthor);
        titleauthor.setTitle(resolvedTitle);
        titleauthor.setAuOrd(auOrd != null ? auOrd : 1);
        titleauthor.setRoyaltyper(royaltyper != null ? royaltyper : 0);

        titleauthorRepository.save(titleauthor);
    }

    @Transactional
    public void addAuthorWithTitle(Author author, Title title, Publisher publisher, Byte auOrd, Integer royaltyper) {
        // Validate required fields
        if (author.getAuId() == null || author.getAuLname() == null || author.getAuFname() == null) {
            throw new IllegalArgumentException("Author ID, last name, and first name are required");
        }
        if (title.getTitleId() == null || title.getTitle() == null) {
            throw new IllegalArgumentException("Title ID and title name are required");
        }

        // Resolve Author
        Author resolvedAuthor = authorRepository.findById(author.getAuId()).orElse(null);
        if (resolvedAuthor == null) {
            List<Author> authorsByName = authorRepository.findByAuLname(author.getAuLname());
            authorsByName.removeIf(a -> !a.getAuFname().equals(author.getAuFname())); // 'author' is effectively final
            if (!authorsByName.isEmpty()) {
                resolvedAuthor = authorsByName.get(0); // Reuse first matching author
            }
        }
        if (resolvedAuthor == null) {
            resolvedAuthor = author;
            if (resolvedAuthor.getPhone() == null) resolvedAuthor.setPhone("UNKNOWN");
            if (resolvedAuthor.getContract() == null) resolvedAuthor.setContract(0);
            authorRepository.save(resolvedAuthor);
        }

        // Handle Publisher (optional)
        if (publisher != null && publisher.getPubId() != null) {
            Publisher existingPublisher = publisherRepository.findById(publisher.getPubId()).orElse(null);
            if (existingPublisher == null) {
                if (publisher.getCountry() == null) publisher.setCountry("USA");
                publisherRepository.save(publisher);
            } else {
                publisher = existingPublisher;
            }
            title.setPub(publisher);
        }

        // Resolve Title
        Title resolvedTitle = titleRepository.findById(title.getTitleId()).orElse(null);
        if (resolvedTitle == null) {
            resolvedTitle = title;
            if (resolvedTitle.getType() == null) resolvedTitle.setType("UNDECIDED");
            if (resolvedTitle.getPubdate() == null) resolvedTitle.setPubdate(Instant.now());
            titleRepository.save(resolvedTitle);
        }

        // Create Titleauthor relationship
        Titleauthor titleauthor = new Titleauthor();
        TitleauthorId id = new TitleauthorId();
        id.setAuId(resolvedAuthor.getAuId());
        id.setTitleId(resolvedTitle.getTitleId());
        titleauthor.setId(id);
        titleauthor.setAu(resolvedAuthor);
        titleauthor.setTitle(resolvedTitle);
        titleauthor.setAuOrd(auOrd != null ? auOrd : 1);
        titleauthor.setRoyaltyper(royaltyper != null ? royaltyper : 0);

        titleauthorRepository.save(titleauthor);
    }
}