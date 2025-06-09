package org.example.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.dto.AuthorDto;
import org.example.backend.entities.Author;
import org.example.backend.mapper.AuthorMapper;
import org.example.backend.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private AuthorMapper authorMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Author author;
    private AuthorDto authorDto;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setAuId("A001");
        author.setAuFname("John");
        author.setAuLname("Doe");
        author.setPhone("1234567890");
        author.setAddress("Main Street");
        author.setCity("Delhi");
        author.setState("DL");
        author.setZip("110001");
        author.setContract(1);

        authorDto = new AuthorDto(
                "A001", "Doe", "John", "1234567890", "Main Street",
                "Delhi", "DL", "110001", 1
        );
    }

    @Test
    void getAllAuthors() throws Exception {
        when(authorRepository.findAll()).thenReturn(List.of(author));
        when(authorMapper.toDto(any())).thenReturn(authorDto);

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].auId").value("A001"));
    }

    @Test
    void createAuthor() throws Exception {
        when(authorMapper.toEntity(any())).thenReturn(author);
        when(authorRepository.save(any())).thenReturn(author);
        when(authorMapper.toDto(any())).thenReturn(authorDto);

        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auId").value("A001"));
    }

    @Test
    void updateAuthor() throws Exception {
        when(authorRepository.findById("A001")).thenReturn(Optional.of(author));
        doNothing().when(authorMapper).partialUpdate(any(), any());
        when(authorRepository.save(any())).thenReturn(author);
        when(authorMapper.toDto(any())).thenReturn(authorDto);

        mockMvc.perform(put("/api/authors/A001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auId").value("A001"));
    }

    @Test
    void getAuthorById() throws Exception {
        when(authorRepository.findById("A001")).thenReturn(Optional.of(author));
        when(authorMapper.toDto(any())).thenReturn(authorDto);

        mockMvc.perform(get("/api/authors/A001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auId").value("A001"));
    }

    @Test
    void getByLastName() throws Exception {
        when(authorRepository.findByAuLname("Doe")).thenReturn(List.of(author));
        when(authorMapper.toDto(any())).thenReturn(authorDto);

        mockMvc.perform(get("/api/authors/lname/Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].auLname").value("Doe"));
    }

    @Test
    void getByFirstName() throws Exception {
        when(authorRepository.findByAuFname("John")).thenReturn(List.of(author));
        when(authorMapper.toDto(any())).thenReturn(authorDto);

        mockMvc.perform(get("/api/authors/fname/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].auFname").value("John"));
    }

    @Test
    void getByPhone() throws Exception {
        when(authorRepository.findByPhone("1234567890")).thenReturn(List.of(author));
        when(authorMapper.toDto(any())).thenReturn(authorDto);

        mockMvc.perform(get("/api/authors/phone/1234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].phone").value("1234567890"));
    }

    @Test
    void getByZip() throws Exception {
        when(authorRepository.findByZip("110001")).thenReturn(List.of(author));
        when(authorMapper.toDto(any())).thenReturn(authorDto);

        mockMvc.perform(get("/api/authors/zip/110001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].zip").value("110001"));
    }

    @Test
    void getByState() throws Exception {
        when(authorRepository.findByState("DL")).thenReturn(List.of(author));
        when(authorMapper.toDto(any())).thenReturn(authorDto);

        mockMvc.perform(get("/api/authors/state/DL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].state").value("DL"));
    }

    @Test
    void getByCity() throws Exception {
        when(authorRepository.findByCity("Delhi")).thenReturn(List.of(author));
        when(authorMapper.toDto(any())).thenReturn(authorDto);

        mockMvc.perform(get("/api/authors/city/Delhi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city").value("Delhi"));
    }
}
