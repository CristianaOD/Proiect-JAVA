package com.library.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.dto.CategorieRequest;
import com.library.management.entity.Categorie;
import com.library.management.service.CategorieService;
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

@WebMvcTest(CategorieController.class)
@SuppressWarnings("deprecation")
class CategorieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategorieService categorieService;

    @Test
    void getAllReturnsList() throws Exception {
        Categorie categorie = new Categorie();
        categorie.setId(1);
        categorie.setNume("Fictiune");

        when(categorieService.getAll()).thenReturn(List.of(categorie));

        mockMvc.perform(get("/categorii"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nume").value("Fictiune"));
    }

    @Test
    void getByIdReturnsCategorie() throws Exception {
        Categorie categorie = new Categorie();
        categorie.setId(2);
        categorie.setNume("Istorie");

        when(categorieService.getById(2)).thenReturn(categorie);

        mockMvc.perform(get("/categorii/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nume").value("Istorie"));
    }

    @Test
    void createReturnsCreated() throws Exception {
        CategorieRequest request = new CategorieRequest();
        request.setNume("Noua");

        Categorie categorie = new Categorie();
        categorie.setId(3);
        categorie.setNume("Noua");

        when(categorieService.create(any(CategorieRequest.class))).thenReturn(categorie);

        mockMvc.perform(post("/categorii")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nume").value("Noua"));
    }

    @Test
    void updateReturnsUpdated() throws Exception {
        CategorieRequest request = new CategorieRequest();
        request.setNume("Actualizata");

        Categorie categorie = new Categorie();
        categorie.setId(4);
        categorie.setNume("Actualizata");

        when(categorieService.update(eq(4), any(CategorieRequest.class))).thenReturn(categorie);

        mockMvc.perform(put("/categorii/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.nume").value("Actualizata"));
    }

    @Test
    void deleteReturnsNoContent() throws Exception {
        doNothing().when(categorieService).delete(5);

        mockMvc.perform(delete("/categorii/5"))
                .andExpect(status().isNoContent());
    }
}





