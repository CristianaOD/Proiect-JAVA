package com.library.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.dto.AutorRequest;
import com.library.management.entity.Autor;
import com.library.management.service.AutorService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AutorController.class)
@SuppressWarnings("deprecation")
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AutorService autorService;

    @Test
    void getAllReturnsList() throws Exception {
        Autor autor = new Autor();
        autor.setId(1);
        autor.setNume("Autor");

        when(autorService.getAll()).thenReturn(List.of(autor));

        mockMvc.perform(get("/autori"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nume").value("Autor"));
    }

    @Test
    void getByIdReturnsAutor() throws Exception {
        Autor autor = new Autor();
        autor.setId(2);
        autor.setNume("Autor 2");

        when(autorService.getById(2)).thenReturn(autor);

        mockMvc.perform(get("/autori/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nume").value("Autor 2"));
    }

    @Test
    void createReturnsCreated() throws Exception {
        AutorRequest request = new AutorRequest();
        request.setNume("Nou");

        Autor autor = new Autor();
        autor.setId(3);
        autor.setNume("Nou");

        when(autorService.create(any(AutorRequest.class))).thenReturn(autor);

        mockMvc.perform(post("/autori")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nume").value("Nou"));
    }

    @Test
    void updateReturnsUpdated() throws Exception {
        AutorRequest request = new AutorRequest();
        request.setNume("Actualizat");

        Autor autor = new Autor();
        autor.setId(4);
        autor.setNume("Actualizat");

        when(autorService.update(eq(4), any(AutorRequest.class))).thenReturn(autor);

        mockMvc.perform(put("/autori/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.nume").value("Actualizat"));
    }

    @Test
    void deleteReturnsNoContent() throws Exception {
        doNothing().when(autorService).delete(5);

        mockMvc.perform(delete("/autori/5"))
                .andExpect(status().isNoContent());
    }
}





