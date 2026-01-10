package com.library.management.controller;

import com.library.management.dto.UtilizatorRequest;
import com.library.management.entity.Utilizator;
import com.library.management.service.UtilizatorService;
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
@RequestMapping("/utilizatori")
@Tag(name = "Utilizatori", description = "Operatii pentru utilizatori.")
public class UtilizatorController {

    private final UtilizatorService utilizatorService;

    public UtilizatorController(UtilizatorService utilizatorService) {
        this.utilizatorService = utilizatorService;
    }

    @GetMapping
    @Operation(summary = "Lista utilizatori", description = "Returneaza toti utilizatorii.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Lista utilizatorilor",
                content = @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Utilizator.class))
                )
        )
    })
    public List<Utilizator> getAll() {
        return utilizatorService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Utilizator dupa id", description = "Returneaza utilizatorul identificat prin id.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Utilizator gasit",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Utilizator.class))
        ),
        @ApiResponse(responseCode = "404", description = "Utilizator negasit")
    })
    public Utilizator getById(@Parameter(description = "ID utilizator", example = "1") @PathVariable Integer id) {
        return utilizatorService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Creeaza utilizator", description = "Creeaza un utilizator nou.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Utilizator creat",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Utilizator.class))
        ),
        @ApiResponse(responseCode = "400", description = "Date invalide")
    })
    public ResponseEntity<Utilizator> create(@Valid @RequestBody UtilizatorRequest request) {
        Utilizator utilizator = utilizatorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilizator);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizeaza utilizator", description = "Actualizeaza un utilizator existent.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Utilizator actualizat",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Utilizator.class))
        ),
        @ApiResponse(responseCode = "400", description = "Date invalide"),
        @ApiResponse(responseCode = "404", description = "Utilizator negasit")
    })
    public Utilizator update(
            @Parameter(description = "ID utilizator", example = "1") @PathVariable Integer id,
            @Valid @RequestBody UtilizatorRequest request
    ) {
        return utilizatorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Sterge utilizator", description = "Sterge utilizatorul dupa id.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Utilizator sters"),
        @ApiResponse(responseCode = "404", description = "Utilizator negasit")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID utilizator", example = "1") @PathVariable Integer id
    ) {
        utilizatorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



