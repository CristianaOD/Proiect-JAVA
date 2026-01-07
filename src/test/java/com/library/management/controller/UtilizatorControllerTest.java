package com.library.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.dto.AdresaRequest;
import com.library.management.dto.UtilizatorRequest;
import com.library.management.entity.Adresa;
import com.library.management.entity.Utilizator;
import com.library.management.service.UtilizatorService;
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

@WebMvcTest(UtilizatorController.class)
@SuppressWarnings("deprecation")
class UtilizatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UtilizatorService utilizatorService;

    @Test
    void getAllReturnsList() throws Exception {
        Utilizator utilizator = buildUtilizator();

        when(utilizatorService.getAll()).thenReturn(List.of(utilizator));

        mockMvc.perform(get("/utilizatori"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("ana@test.com"));
    }

    @Test
    void getByIdReturnsUtilizator() throws Exception {
        Utilizator utilizator = buildUtilizator();

        when(utilizatorService.getById(1)).thenReturn(utilizator);

        mockMvc.perform(get("/utilizatori/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.adresa.oras").value("Bucuresti"));
    }

    @Test
    void createReturnsCreated() throws Exception {
        UtilizatorRequest request = buildUtilizatorRequest();
        Utilizator utilizator = buildUtilizator();

        when(utilizatorService.create(any(UtilizatorRequest.class))).thenReturn(utilizator);

        mockMvc.perform(post("/utilizatori")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nume").value("Ana"));
    }

    @Test
    void updateReturnsUpdated() throws Exception {
        UtilizatorRequest request = buildUtilizatorRequest();
        Utilizator utilizator = buildUtilizator();

        when(utilizatorService.update(eq(1), any(UtilizatorRequest.class))).thenReturn(utilizator);

        mockMvc.perform(put("/utilizatori/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.prenume").value("Pop"));
    }

    @Test
    void deleteReturnsNoContent() throws Exception {
        doNothing().when(utilizatorService).delete(1);

        mockMvc.perform(delete("/utilizatori/1"))
                .andExpect(status().isNoContent());
    }

    private Utilizator buildUtilizator() {
        Adresa adresa = new Adresa();
        adresa.setId(10);
        adresa.setOras("Bucuresti");
        adresa.setStrada("Strada 1");
        adresa.setCodPostal("010101");

        Utilizator utilizator = new Utilizator();
        utilizator.setId(1);
        utilizator.setNume("Ana");
        utilizator.setPrenume("Pop");
        utilizator.setEmail("ana@test.com");
        utilizator.setTelefon("0700");
        utilizator.setAdresa(adresa);
        return utilizator;
    }

    private UtilizatorRequest buildUtilizatorRequest() {
        AdresaRequest adresaRequest = new AdresaRequest();
        adresaRequest.setOras("Bucuresti");
        adresaRequest.setStrada("Strada 1");
        adresaRequest.setCodPostal("010101");

        UtilizatorRequest request = new UtilizatorRequest();
        request.setNume("Ana");
        request.setPrenume("Pop");
        request.setEmail("ana@test.com");
        request.setTelefon("0700");
        request.setAdresa(adresaRequest);
        return request;
    }
}





