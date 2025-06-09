package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthorWithBooksDto {
    private String auId;
    private String auFname;
    private String auLname;
    private List<String> titles;
}
