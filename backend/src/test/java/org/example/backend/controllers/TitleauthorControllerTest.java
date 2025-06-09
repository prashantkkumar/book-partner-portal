package org.example.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.dto.TitleauthorDto;
import org.example.backend.dto.TitleauthorIdDto;
import org.example.backend.entities.Titleauthor;
import org.example.backend.mapper.TitleauthorMapper;
import org.example.backend.repository.AuthorRepository;
import org.example.backend.repository.TitleRepository;
import org.example.backend.repository.TitleauthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TitleauthorController.class)
class TitleauthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TitleauthorRepository titleauthorRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private TitleRepository titleRepository;

    @MockBean
    private TitleauthorMapper titleauthorMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private TitleauthorDto sampleDto;
    private Titleauthor sampleEntity;

    @BeforeEach
    void setup() {
        TitleauthorIdDto idDto = new TitleauthorIdDto("123", "T001");
        sampleDto = new TitleauthorDto(idDto, null, null, (byte) 1, 20);
        sampleEntity = new Titleauthor();
    }

    @Test
    void testGetAllMappings() throws Exception {
        when(titleauthorRepository.findAll()).thenReturn(List.of(sampleEntity));
        when(titleauthorMapper.toDto(sampleEntity)).thenReturn(sampleDto);

        mockMvc.perform(get("/api/titleauthor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id.auId").value("123"));
    }

    @Test
    void testGetTitlesByAuthor_Found() throws Exception {
        when(authorRepository.existsById("123")).thenReturn(true);
        when(titleauthorRepository.findByAu_AuId("123")).thenReturn(List.of(sampleEntity));
        when(titleauthorMapper.toDto(sampleEntity)).thenReturn(sampleDto);

        mockMvc.perform(get("/api/titleauthor/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id.auId").value("123"));
    }

    @Test
    void testGetTitlesByAuthor_NotFound() throws Exception {
        when(authorRepository.existsById("123")).thenReturn(false);

        mockMvc.perform(get("/api/titleauthor/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAssignTitleToAuthor_Success() throws Exception {
        when(authorRepository.existsById("123")).thenReturn(true);
        when(titleRepository.existsById("T001")).thenReturn(true);
        when(titleauthorMapper.toEntity(any())).thenReturn(sampleEntity);
        when(titleauthorRepository.save(any())).thenReturn(sampleEntity);
        when(titleauthorMapper.toDto(any())).thenReturn(sampleDto);

        mockMvc.perform(post("/api/titleauthor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id.titleId").value("T001"));
    }

    @Test
    void testAssignTitleToAuthor_BadRequest() throws Exception {
        when(authorRepository.existsById("123")).thenReturn(false);
        when(titleRepository.existsById("T001")).thenReturn(true);

        mockMvc.perform(post("/api/titleauthor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateTitleAuthor_Success() throws Exception {
        when(titleauthorRepository.findById(any())).thenReturn(java.util.Optional.of(sampleEntity));
        doNothing().when(titleauthorMapper).partialUpdate(any(), any());
        when(titleauthorRepository.save(any())).thenReturn(sampleEntity);
        when(titleauthorMapper.toDto(any())).thenReturn(sampleDto);

        mockMvc.perform(put("/api/titleauthor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id.auId").value("123"));
    }

    @Test
    void testUpdateTitleAuthor_NotFound() throws Exception {
        when(titleauthorRepository.findById(any())).thenReturn(java.util.Optional.empty());

        mockMvc.perform(put("/api/titleauthor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleDto)))
                .andExpect(status().isNotFound());
    }
}
