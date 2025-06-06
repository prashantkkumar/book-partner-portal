package org.example.backend.dto;

import lombok.Value;
import org.example.backend.entities.Author;

import java.io.Serializable;

/**
 * DTO for {@link Author}
 */
@Value
public class AuthorDto implements Serializable {
    String auId;
    String auLname;
    String auFname;
    String phone;
    String address;
    String city;
    String state;
    String zip;
    Integer contract;
}