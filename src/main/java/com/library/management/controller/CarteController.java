package com.library.management.controller;

import com.library.management.dto.CarteRequest;
import com.library.management.entity.Carte;
import com.library.management.service.CarteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carti")
public class CarteController {

    private final CarteService carteService;

    public CarteController(CarteService carteService) {
        this.carteService = carteService;
    }

    @GetMapping
    public List<Carte> getAll() {
        return carteService.getAll();
    }

    @GetMapping("/{id}")
    public Carte getById(@PathVariable Integer id) {
        return carteService.getById(id);
    }

    @GetMapping("/cautare")
    public List<Carte> cautaDupaTitlu(@RequestParam("titlu") String titlu) {
        return carteService.cautaDupaTitlu(titlu);
    }

    @PostMapping
    public ResponseEntity<Carte> create(@Valid @RequestBody CarteRequest request) {
        Carte carte = carteService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(carte);
    }

    @PutMapping("/{id}")
    public Carte update(@PathVariable Integer id, @Valid @RequestBody CarteRequest request) {
        return carteService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        carteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



