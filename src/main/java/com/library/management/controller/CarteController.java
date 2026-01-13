package com.library.management.controller;

import com.library.management.dto.CarteRequest;
import com.library.management.entity.Carte;
import com.library.management.service.CarteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Carti", description = "Operatii pentru carti.")
public class CarteController {

    private final CarteService carteService;

    public CarteController(CarteService carteService) {
        this.carteService = carteService;
    }

    @GetMapping
    @Operation(summary = "Lista carti", description = "Returneaza toate cartile.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Lista cartilor",
                content = @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Carte.class))
                )
        )
    })
    public List<Carte> getAll() {
        return carteService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Carte dupa id", description = "Returneaza cartea identificata prin id.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Carte gasita",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carte.class))
        ),
        @ApiResponse(responseCode = "404", description = "Carte negasita")
    })
    public Carte getById(@Parameter(description = "ID carte", example = "1") @PathVariable Integer id) {
        return carteService.getById(id);
    }

    @GetMapping("/cautare")
    @Operation(summary = "Cauta carti dupa titlu", description = "Returneaza cartile care contin fragmentul in titlu.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Lista cartilor",
                content = @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Carte.class))
                )
        )
    })
    public List<Carte> cautaDupaTitlu(
            @Parameter(description = "Fragment de titlu", example = "Luceafarul")
            @RequestParam("titlu") String titlu
    ) {
        return carteService.cautaDupaTitlu(titlu);
    }

    @PostMapping
    @Operation(summary = "Creeaza carte", description = "Creeaza o carte noua.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Carte creata",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carte.class))
        ),
        @ApiResponse(responseCode = "400", description = "Date invalide"),
        @ApiResponse(responseCode = "404", description = "Autor sau categorie negasita")
    })
    public ResponseEntity<Carte> create(@Valid @RequestBody CarteRequest request) {
        Carte carte = carteService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(carte);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizeaza carte", description = "Actualizeaza o carte existenta.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Carte actualizata",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carte.class))
        ),
        @ApiResponse(responseCode = "400", description = "Date invalide"),
        @ApiResponse(responseCode = "404", description = "Carte, autor sau categorie negasita")
    })
    public Carte update(
            @Parameter(description = "ID carte", example = "1") @PathVariable Integer id,
            @Valid @RequestBody CarteRequest request
    ) {
        return carteService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Sterge carte", description = "Sterge cartea dupa id.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Carte stearsa"),
        @ApiResponse(responseCode = "404", description = "Carte negasita")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "ID carte", example = "1") @PathVariable Integer id) {
        carteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



