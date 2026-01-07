package com.library.management.controller;

import com.library.management.dto.AutorRequest;
import com.library.management.entity.Autor;
import com.library.management.service.AutorService;
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
@RequestMapping("/autori")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public List<Autor> getAll() {
        return autorService.getAll();
    }

    @GetMapping("/{id}")
    public Autor getById(@PathVariable Integer id) {
        return autorService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Autor> create(@Valid @RequestBody AutorRequest request) {
        Autor autor = autorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(autor);
    }

    @PutMapping("/{id}")
    public Autor update(@PathVariable Integer id, @Valid @RequestBody AutorRequest request) {
        return autorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



