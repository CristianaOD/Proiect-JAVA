package com.library.management.controller;

import com.library.management.dto.AutorRequest;
import com.library.management.entity.Autor;
import com.library.management.service.AutorService;
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
@RequestMapping("/autori")
@Tag(name = "Autori", description = "Operatii pentru autori.")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    @Operation(summary = "Lista autori", description = "Returneaza toti autorii.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Lista autorilor",
                content = @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Autor.class))
                )
        )
    })
    public List<Autor> getAll() {
        return autorService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Autor dupa id", description = "Returneaza autorul identificat prin id.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Autor gasit",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Autor.class))
        ),
        @ApiResponse(responseCode = "404", description = "Autor negasit")
    })
    public Autor getById(@Parameter(description = "ID autor", example = "1") @PathVariable Integer id) {
        return autorService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Creeaza autor", description = "Creeaza un autor nou.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Autor creat",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Autor.class))
        ),
        @ApiResponse(responseCode = "400", description = "Date invalide")
    })
    public ResponseEntity<Autor> create(@Valid @RequestBody AutorRequest request) {
        Autor autor = autorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(autor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizeaza autor", description = "Actualizeaza un autor existent.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Autor actualizat",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Autor.class))
        ),
        @ApiResponse(responseCode = "400", description = "Date invalide"),
        @ApiResponse(responseCode = "404", description = "Autor negasit")
    })
    public Autor update(
            @Parameter(description = "ID autor", example = "1") @PathVariable Integer id,
            @Valid @RequestBody AutorRequest request
    ) {
        return autorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Sterge autor", description = "Sterge autorul dupa id.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Autor sters"),
        @ApiResponse(responseCode = "404", description = "Autor negasit")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "ID autor", example = "1") @PathVariable Integer id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



