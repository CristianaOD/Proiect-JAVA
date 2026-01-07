package com.library.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.dto.CarteRequest;
import com.library.management.entity.Autor;
import com.library.management.entity.Carte;
import com.library.management.entity.Categorie;
import com.library.management.service.CarteService;
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

@WebMvcTest(CarteController.class)
@SuppressWarnings("deprecation")
class CarteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarteService carteService;

    @Test
    void getAllReturnsList() throws Exception {
        Carte carte = buildCarte();

        when(carteService.getAll()).thenReturn(List.of(carte));

        mockMvc.perform(get("/carti"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].titlu").value("Titlu"))
                .andExpect(jsonPath("$[0].autor.nume").value("Autor"));
    }

    @Test
    void getByIdReturnsCarte() throws Exception {
        Carte carte = buildCarte();

        when(carteService.getById(3)).thenReturn(carte);

        mockMvc.perform(get("/carti/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.titlu").value("Titlu"))
                .andExpect(jsonPath("$.categorie.nume").value("Categorie"));
    }

    @Test
    void cautareReturnsList() throws Exception {
        Carte carte = buildCarte();

        when(carteService.cautaDupaTitlu("Titlu")).thenReturn(List.of(carte));

        mockMvc.perform(get("/carti/cautare").param("titlu", "Titlu"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3));
    }

    @Test
    void createReturnsCreated() throws Exception {
        CarteRequest request = new CarteRequest();
        request.setTitlu("Titlu");
        request.setStoc(5);
        request.setAutorId(1);
        request.setCategorieId(2);

        Carte carte = buildCarte();

        when(carteService.create(any(CarteRequest.class))).thenReturn(carte);

        mockMvc.perform(post("/carti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.titlu").value("Titlu"));
    }

    @Test
    void updateReturnsUpdated() throws Exception {
        CarteRequest request = new CarteRequest();
        request.setTitlu("Titlu");
        request.setStoc(5);
        request.setAutorId(1);
        request.setCategorieId(2);

        Carte carte = buildCarte();

        when(carteService.update(eq(3), any(CarteRequest.class))).thenReturn(carte);

        mockMvc.perform(put("/carti/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.titlu").value("Titlu"));
    }

    @Test
    void deleteReturnsNoContent() throws Exception {
        doNothing().when(carteService).delete(3);

        mockMvc.perform(delete("/carti/3"))
                .andExpect(status().isNoContent());
    }

    private Carte buildCarte() {
        Autor autor = new Autor();
        autor.setId(1);
        autor.setNume("Autor");

        Categorie categorie = new Categorie();
        categorie.setId(2);
        categorie.setNume("Categorie");

        Carte carte = new Carte();
        carte.setId(3);
        carte.setTitlu("Titlu");
        carte.setStoc(5);
        carte.setAutor(autor);
        carte.setCategorie(categorie);
        return carte;
    }
}





