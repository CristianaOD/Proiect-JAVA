package com.library.management.controller;

import com.library.management.dto.UtilizatorRequest;
import com.library.management.entity.Utilizator;
import com.library.management.service.UtilizatorService;
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
@RequestMapping("/utilizatori")
public class UtilizatorController {

    private final UtilizatorService utilizatorService;

    public UtilizatorController(UtilizatorService utilizatorService) {
        this.utilizatorService = utilizatorService;
    }

    @GetMapping
    public List<Utilizator> getAll() {
        return utilizatorService.getAll();
    }

    @GetMapping("/{id}")
    public Utilizator getById(@PathVariable Integer id) {
        return utilizatorService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Utilizator> create(@Valid @RequestBody UtilizatorRequest request) {
        Utilizator utilizator = utilizatorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilizator);
    }

    @PutMapping("/{id}")
    public Utilizator update(@PathVariable Integer id, @Valid @RequestBody UtilizatorRequest request) {
        return utilizatorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        utilizatorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



