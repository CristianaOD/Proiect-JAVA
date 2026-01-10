package com.library.management.controller;

import com.library.management.dto.ImprumutRequest;
import com.library.management.entity.Imprumut;
import com.library.management.service.ImprumutService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imprumuturi")
@Tag(name = "Imprumuturi", description = "Operatii pentru imprumuturi.")
public class ImprumutController {

    private final ImprumutService imprumutService;

    public ImprumutController(ImprumutService imprumutService) {
        this.imprumutService = imprumutService;
    }

    @GetMapping
    @Operation(summary = "Lista imprumuturi", description = "Returneaza toate imprumuturile.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Lista imprumuturilor",
                content = @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Imprumut.class))
                )
        )
    })
    public List<Imprumut> getAll() {
        return imprumutService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Imprumut dupa id", description = "Returneaza imprumutul identificat prin id.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Imprumut gasit",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Imprumut.class))
        ),
        @ApiResponse(responseCode = "404", description = "Imprumut negasit")
    })
    public Imprumut getById(@Parameter(description = "ID imprumut", example = "1") @PathVariable Integer id) {
        return imprumutService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Creeaza imprumut", description = "Inregistreaza un imprumut nou.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Imprumut creat",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Imprumut.class))
        ),
        @ApiResponse(responseCode = "400", description = "Stoc indisponibil sau date invalide"),
        @ApiResponse(responseCode = "404", description = "Utilizator sau carte negasita")
    })
    public ResponseEntity<Imprumut> imprumuta(@Valid @RequestBody ImprumutRequest request) {
        Imprumut imprumut = imprumutService.imprumutaCarte(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(imprumut);
    }

    @PostMapping("/{id}/returnare")
    @Operation(summary = "Returneaza carte", description = "Marcheaza imprumutul ca returnat.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Imprumut returnat",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Imprumut.class))
        ),
        @ApiResponse(responseCode = "400", description = "Imprumut deja returnat"),
        @ApiResponse(responseCode = "404", description = "Imprumut negasit")
    })
    public Imprumut returneaza(@Parameter(description = "ID imprumut", example = "1") @PathVariable Integer id) {
        return imprumutService.returneazaCarte(id);
    }

    @GetMapping("/istoric/{utilizatorId}")
    @Operation(summary = "Istoric imprumuturi", description = "Returneaza istoricul unui utilizator.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Istoric utilizator",
                content = @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Imprumut.class))
                )
        ),
        @ApiResponse(responseCode = "404", description = "Utilizator negasit")
    })
    public List<Imprumut> istoric(
            @Parameter(description = "ID utilizator", example = "1") @PathVariable Integer utilizatorId
    ) {
        return imprumutService.istoricUtilizator(utilizatorId);
    }
}



