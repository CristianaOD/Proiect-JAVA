package com.library.management.controller;

import com.library.management.dto.ImprumutRequest;
import com.library.management.entity.Imprumut;
import com.library.management.service.ImprumutService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imprumuturi")
public class ImprumutController {

    private final ImprumutService imprumutService;

    public ImprumutController(ImprumutService imprumutService) {
        this.imprumutService = imprumutService;
    }

    @GetMapping
    public List<Imprumut> getAll() {
        return imprumutService.getAll();
    }

    @GetMapping("/{id}")
    public Imprumut getById(@PathVariable Integer id) {
        return imprumutService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Imprumut> imprumuta(@Valid @RequestBody ImprumutRequest request) {
        Imprumut imprumut = imprumutService.imprumutaCarte(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(imprumut);
    }

    @PostMapping("/{id}/returnare")
    public Imprumut returneaza(@PathVariable Integer id) {
        return imprumutService.returneazaCarte(id);
    }

    @GetMapping("/istoric/{utilizatorId}")
    public List<Imprumut> istoric(@PathVariable Integer utilizatorId) {
        return imprumutService.istoricUtilizator(utilizatorId);
    }
}



