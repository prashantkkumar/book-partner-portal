package org.example.backend.dto;

public record AuthorTitleCountDto(
        String auId,
        String auFname,
        String auLname,
        Long titleCount
) {}
