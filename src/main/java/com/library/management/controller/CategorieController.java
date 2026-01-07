package com.library.management.controller;

import com.library.management.dto.CategorieRequest;
import com.library.management.entity.Categorie;
import com.library.management.service.CategorieService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorii")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public List<Categorie> getAll() {
        return categorieService.getAll();
    }

    @GetMapping("/{id}")
    public Categorie getById(@PathVariable Integer id) {
        return categorieService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Categorie> create(@Valid @RequestBody CategorieRequest request) {
        Categorie categorie = categorieService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorie);
    }

    @PutMapping("/{id}")
    public Categorie update(@PathVariable Integer id, @Valid @RequestBody CategorieRequest request) {
        return categorieService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categorieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



