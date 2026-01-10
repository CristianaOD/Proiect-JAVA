package com.library.management.controller;

import com.library.management.dto.CategorieRequest;
import com.library.management.entity.Categorie;
import com.library.management.service.CategorieService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorii")
@Tag(name = "Categorii", description = "Operatii pentru categorii.")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    @Operation(summary = "Lista categorii", description = "Returneaza toate categoriile.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Lista categoriilor",
                content = @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Categorie.class))
                )
        )
    })
    public List<Categorie> getAll() {
        return categorieService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Categorie dupa id", description = "Returneaza categoria identificata prin id.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Categorie gasita",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categorie.class))
        ),
        @ApiResponse(responseCode = "404", description = "Categorie negasita")
    })
    public Categorie getById(@Parameter(description = "ID categorie", example = "1") @PathVariable Integer id) {
        return categorieService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Creeaza categorie", description = "Creeaza o categorie noua.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Categorie creata",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categorie.class))
        ),
        @ApiResponse(responseCode = "400", description = "Date invalide")
    })
    public ResponseEntity<Categorie> create(@Valid @RequestBody CategorieRequest request) {
        Categorie categorie = categorieService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorie);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizeaza categorie", description = "Actualizeaza o categorie existenta.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Categorie actualizata",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categorie.class))
        ),
        @ApiResponse(responseCode = "400", description = "Date invalide"),
        @ApiResponse(responseCode = "404", description = "Categorie negasita")
    })
    public Categorie update(
            @Parameter(description = "ID categorie", example = "1") @PathVariable Integer id,
            @Valid @RequestBody CategorieRequest request
    ) {
        return categorieService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Sterge categorie", description = "Sterge categoria dupa id.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Categorie stearsa"),
        @ApiResponse(responseCode = "404", description = "Categorie negasita")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "ID categorie", example = "1") @PathVariable Integer id) {
        categorieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



