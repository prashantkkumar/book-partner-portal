package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorBookDto {
    private String auId;
    private String auFname;
    private String auLname;
    private String title;
}

