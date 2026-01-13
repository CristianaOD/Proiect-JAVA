package com.library.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.dto.ImprumutRequest;
import com.library.management.entity.Carte;
import com.library.management.entity.Imprumut;
import com.library.management.entity.Utilizator;
import com.library.management.service.ImprumutService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ImprumutController.class)
@SuppressWarnings("deprecation")
class ImprumutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ImprumutService imprumutService;

    @Test
    void getAllReturnsList() throws Exception {
        Imprumut imprumut = buildImprumut();

        when(imprumutService.getAll()).thenReturn(List.of(imprumut));

        mockMvc.perform(get("/imprumuturi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void getByIdReturnsImprumut() throws Exception {
        Imprumut imprumut = buildImprumut();

        when(imprumutService.getById(1)).thenReturn(imprumut);

        mockMvc.perform(get("/imprumuturi/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void imprumutaReturnsCreated() throws Exception {
        ImprumutRequest request = new ImprumutRequest();
        request.setUtilizatorId(1);
        request.setCarteId(2);

        Imprumut imprumut = buildImprumut();

        when(imprumutService.imprumutaCarte(any(ImprumutRequest.class))).thenReturn(imprumut);

        mockMvc.perform(post("/imprumuturi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void returneazaReturnsUpdated() throws Exception {
        Imprumut imprumut = buildImprumut();
        imprumut.setDataRetur(LocalDateTime.now());

        when(imprumutService.returneazaCarte(1)).thenReturn(imprumut);

        mockMvc.perform(post("/imprumuturi/1/returnare"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void istoricReturnsList() throws Exception {
        Imprumut imprumut = buildImprumut();

        when(imprumutService.istoricUtilizator(eq(1))).thenReturn(List.of(imprumut));

        mockMvc.perform(get("/imprumuturi/istoric/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    private Imprumut buildImprumut() {
        Utilizator utilizator = new Utilizator();
        utilizator.setId(1);

        Carte carte = new Carte();
        carte.setId(2);
        carte.setStoc(3);

        Imprumut imprumut = new Imprumut();
        imprumut.setId(1);
        imprumut.setUtilizator(utilizator);
        imprumut.setCarte(carte);
        imprumut.setDataImprumut(LocalDateTime.now());
        return imprumut;
    }
}





